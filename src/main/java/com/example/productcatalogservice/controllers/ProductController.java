package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    //  GET /products
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("{Id:[\\\\d]+}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("Id") Long productId) {
        if(productId < 0){
            throw new IllegalArgumentException("Product Id must be greater than 0");
        }
        Product product = productService.getProductById(productId);
        return product != null ? new ResponseEntity<>(from(product), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto input) {
        Product inputProduct = from(input);
        Product product = productService.createProduct(inputProduct);
        return new ResponseEntity<>(from(product), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id,
                                     @RequestBody ProductDto input)  {
        Product inputProduct = from(input);
        Product product = productService.replaceProduct(inputProduct,id);
        return new ResponseEntity<>(from(product), HttpStatus.OK);
    }


    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setTitle(product.getCategory().getTitle());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setTitle(productDto.getCategory().getTitle());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }
}
