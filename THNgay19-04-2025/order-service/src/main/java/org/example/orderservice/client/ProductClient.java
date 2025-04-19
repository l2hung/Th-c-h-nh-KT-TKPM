package org.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service", url = "http://localhost:8082", fallback = ProductFallback.class)
public interface ProductClient {
    @GetMapping("/api/products")
    String getProducts();
}
