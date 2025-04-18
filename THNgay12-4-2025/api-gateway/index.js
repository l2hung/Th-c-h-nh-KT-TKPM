const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();

// Middleware để parse JSON
app.use(express.json());

// Route mặc định cho endpoint gốc "/"
app.get('/', (req, res) => {
  res.send('Welcome to the API Gateway!');
});

// Định tuyến đến Customer Service
app.use(
  '/customers',
  createProxyMiddleware({
    target: process.env.CUSTOMER_SERVICE_URL || 'http://localhost:8001',
    changeOrigin: true,
  })
);

// Định tuyến đến Order Service
app.use(
  '/orders',
  createProxyMiddleware({
    target: process.env.ORDER_SERVICE_URL || 'http://localhost:8002',
    changeOrigin: true,
  })
);

// Định tuyến đến Product Service
app.use(
  '/products',
  createProxyMiddleware({
    target: process.env.PRODUCT_SERVICE_URL || 'http://localhost:8003',
    changeOrigin: true,
  })
);

// Khởi chạy API Gateway
const PORT = process.env.API_GATEWAY_PORT || 3004;
app.listen(PORT, () => {
  console.log(`API Gateway is running on port ${PORT}`);
});