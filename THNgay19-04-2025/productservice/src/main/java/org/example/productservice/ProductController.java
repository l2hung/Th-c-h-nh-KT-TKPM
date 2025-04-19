package org.example.productservice;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Map<String, Object>> products = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getProducts() {
        return products;
    }

    @PostMapping
    public Map<String, Object> addProduct(@RequestBody Map<String, Object> product) {
        products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateProduct(@PathVariable int id, @RequestBody Map<String, Object> updatedProduct) {
        for (Map<String, Object> p : products) {
            if (Objects.equals(p.get("id"), id)) {
                p.putAll(updatedProduct);
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        products.removeIf(p -> Objects.equals(p.get("id"), id));
        return "Product with ID " + id + " deleted.";
    }
}
