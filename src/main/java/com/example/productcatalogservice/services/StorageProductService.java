package com.example.productcatalogservice.services;

import com.example.productcatalogservice.exceptions.ProductAlreadyExistsException;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.models.State;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            return  productOptional.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepo.findById(product.getId());
        if(productOptional.isEmpty()) {
            return productRepo.save(product);
        } else {
            throw new ProductAlreadyExistsException("Please try out some other product id");
        }
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        input.setId(id);
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()) {
            input.setUpdatedAt(new Date());
            return productRepo.save(input);
        } else {
            throw new ProductNotFoundException("product not found with this id");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("product not found with this id");
        } else {
            Product p = product.get();
            if(p.getState().equals(State.ACTIVE)) {
                p.setState(State.INACTIVE);
                productRepo.save(p);
            } else {
                productRepo.deleteById(id);
            }
        }
    }
}
