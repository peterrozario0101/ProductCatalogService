package com.example.produccatalogservice.controllers;

import com.example.produccatalogservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product product = new Product();
        product.setId("1");
        product.setTitle("Product 1");
        product.setDescription("Description 1");
        product.setPrice(10.0);
        product.setState(com.example.produccatalogservice.models.State.ACTIVE);
        return List.of(product);
    }
}
