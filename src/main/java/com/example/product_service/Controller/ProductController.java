package com.example.product_service.Controller;

import com.example.product_service.Model.Product;
import com.example.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public Product create(@RequestBody Map<String,Object> body) {
        String name = (String) body.get("name");
        BigDecimal price = new BigDecimal(body.get("price").toString());
        return service.create(name, price);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}