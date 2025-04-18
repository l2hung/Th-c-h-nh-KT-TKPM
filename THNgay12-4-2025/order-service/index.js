const express = require('express');
const { Pool } = require('pg');
const app = express();

app.use(express.json());

// Cấu hình kết nối PostgreSQL từ biến môi trường
const pool = new Pool({
  user: process.env.POSTGRES_USER || 'postgres',
  host: process.env.POSTGRES_HOST || 'localhost',
  database: process.env.POSTGRES_DB || 'order_service',
  password: process.env.POSTGRES_PASSWORD || 'password',
  port: process.env.POSTGRES_PORT || 5432,
});

// Tạo bảng nếu chưa tồn tại
pool.query(`
  CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(50) DEFAULT 'pending'
  )
`).catch(err => console.error('Error creating table:', err));

// Lấy danh sách đơn hàng
app.get('/orders', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM orders');
    res.json(result.rows);
  } catch (err) {
    res.status(500).send('Error fetching orders');
  }
});

// Lấy thông tin đơn hàng theo ID
app.get('/orders/:id', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM orders WHERE id = $1', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error fetching order');
  }
});

// Tạo đơn hàng mới
app.post('/orders', async (req, res) => {
  const { customer_id, product_id, quantity, status } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO orders (customer_id, product_id, quantity, status) VALUES ($1, $2, $3, $4) RETURNING *',
      [customer_id, product_id, quantity, status || 'pending']
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error creating order');
  }
});

// Cập nhật đơn hàng
app.put('/orders/:id', async (req, res) => {
  const { customer_id, product_id, quantity, status } = req.body;
  try {
    const result = await pool.query(
      'UPDATE orders SET customer_id = $1, product_id = $2, quantity = $3, status = $4 WHERE id = $5 RETURNING *',
      [customer_id, product_id, quantity, status, req.params.id]
    );
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error updating order');
  }
});

// Xóa đơn hàng
app.delete('/orders/:id', async (req, res) => {
  try {
    const result = await pool.query('DELETE FROM orders WHERE id = $1 RETURNING *', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.sendStatus(204);
  } catch (err) {
    res.status(500).send('Error deleting order');
  }
});

// Khởi chạy server
app.listen(8000, () => console.log('Order service on port 8000'));
