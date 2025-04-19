package org.example.orderservice.client;

import org.springframework.stereotype.Component;

@Component
public class ProductFallback implements ProductClient {
    @Override
    public String getProducts() {
        return "Fallback: product-service is not available";
    }
}
