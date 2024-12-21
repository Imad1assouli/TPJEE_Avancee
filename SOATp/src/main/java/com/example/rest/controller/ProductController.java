package com.example.rest.controller;

import com.example.rest.model.Product;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final List<Product> productList = new ArrayList<>();

    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productList.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productList.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productList.removeIf(product -> Objects.equals(product.getId(), id));
    }
}
