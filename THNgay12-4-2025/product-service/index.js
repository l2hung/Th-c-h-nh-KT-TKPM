const express = require('express');
const { Pool } = require('pg');
const app = express();

app.use(express.json());

// Cấu hình kết nối PostgreSQL từ biến môi trường
const pool = new Pool({
  user: process.env.POSTGRES_USER || 'postgres',
  host: process.env.POSTGRES_HOST || 'localhost',
  database: process.env.POSTGRES_DB || 'product_service',
  password: process.env.POSTGRES_PASSWORD || 'password',
  port: process.env.POSTGRES_PORT || 5432,
});

// Tạo bảng nếu chưa tồn tại
pool.query(`
  CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    stock INT NOT NULL
  )
`).catch(err => console.error('Error creating table:', err));

// Lấy danh sách sản phẩm
app.get('/products', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM products');
    res.json(result.rows);
  } catch (err) {
    res.status(500).send('Error fetching products');
  }
});

// Lấy thông tin sản phẩm theo ID
app.get('/products/:id', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM products WHERE id = $1', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error fetching product');
  }
});

// Thêm sản phẩm mới
app.post('/products', async (req, res) => {
  const { name, description, price, stock } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO products (name, description, price, stock) VALUES ($1, $2, $3, $4) RETURNING *',
      [name, description, price, stock]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error creating product');
  }
});

// Cập nhật thông tin sản phẩm
app.put('/products/:id', async (req, res) => {
  const { name, description, price, stock } = req.body;
  try {
    const result = await pool.query(
      'UPDATE products SET name = $1, description = $2, price = $3, stock = $4 WHERE id = $5 RETURNING *',
      [name, description, price, stock, req.params.id]
    );
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).send('Error updating product');
  }
});

// Xóa sản phẩm
app.delete('/products/:id', async (req, res) => {
  try {
    const result = await pool.query('DELETE FROM products WHERE id = $1 RETURNING *', [req.params.id]);
    if (result.rows.length === 0) return res.status(404).send('Not found');
    res.sendStatus(204);
  } catch (err) {
    res.status(500).send('Error deleting product');
  }
});

// Khởi chạy server
app.listen(8000, () => console.log('Product service on port 8000'));
