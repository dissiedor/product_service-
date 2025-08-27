package com.example.product_service.Service;

import com.example.product_service.Dto.ProductEvent;
import com.example.product_service.Model.Product;
import com.example.product_service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;
    @Value("${app.topics.product-events}") private String topic;

    public Product create(String name, BigDecimal price) {
        Product saved = repo.save(new Product(null, name, price));
        kafkaTemplate.send(topic, new ProductEvent(saved.getId(), saved.getName(), saved.getPrice(), "CREATED"));
        return saved;
    }

    public Optional<Product> get(Long id) { return repo.findById(id); }
}
