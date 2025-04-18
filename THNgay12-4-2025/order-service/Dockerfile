# Dockerfile (ví dụ cho customer-service)
FROM node:18

WORKDIR /app

# Sao chép file package.json và package-lock.json
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Sao chép toàn bộ mã nguồn
COPY . .

# Mở cổng
EXPOSE 8001

# Lệnh khởi chạy
CMD ["node", "index.js"]