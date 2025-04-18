const express = require('express');
const { Pool } = require('pg');
const app = express();

app.use(express.json());

// Cấu hình kết nối PostgreSQL từ biến môi trường
const pool = new Pool({
  user: process.env.POSTGRES_USER || 'postgres',
  host: process.env.POSTGRES_HOST || 'localhost',
  database: process.env.POSTGRES_DB || 'customer_service',
  password: process.env.POSTGRES_PASSWORD || 'password',
  port: process.env.POSTGRES_PORT || 5432,
});

// Tạo bảng nếu chưa tồn tại
pool.query(`
  CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20)
  )
`).catch(err => console.error('Error creating table:', err));

// Lấy danh sách khách hàng
app.get('/customers', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM customers');
    res.json(result.rows);
  } catch (err) {
    res.status(500).send('Error fetching customers');
  }
});

// Lấy thông tin khách hàng theo ID
app.get('/customers/:id', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM customers WHERE id = $1', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error fetching customer');
  }
});

// Thêm khách hàng mới
app.post('/customers', async (req, res) => {
  const { name, email, phone } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO customers (name, email, phone) VALUES ($1, $2, $3) RETURNING *',
      [name, email, phone]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error creating customer');
  }
});

// Cập nhật thông tin khách hàng
app.put('/customers/:id', async (req, res) => {
  const { name, email, phone } = req.body;
  try {
    const result = await pool.query(
      'UPDATE customers SET name = $1, email = $2, phone = $3 WHERE id = $4 RETURNING *',
      [name, email, phone, req.params.id]
    );
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error updating customer');
  }
});

// Xóa khách hàng
app.delete('/customers/:id', async (req, res) => {
  try {
    const result = await pool.query('DELETE FROM customers WHERE id = $1 RETURNING *', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.sendStatus(204);
  } catch (err) {
    res.status(500).send('Error deleting customer');
  }
});

// Khởi chạy server
app.listen(8000, () => console.log('Customer service on port 8000'));
