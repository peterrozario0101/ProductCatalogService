package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final RestClient client;

    public FakeStoreProductService(RestClient.Builder builder) {
        this.client = builder.baseUrl("https://fakestoreapi.com").build();
    }

    @Override
    public Product getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
                client.get().uri("/products/{id}", id).retrieve().toEntity(FakeStoreProductDto.class);

        if (validateFakeStoreResponse(fakeStoreProductDto)) {
            return from(fakeStoreProductDto.getBody());
        }

        return null;    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = client.get().uri("/products").retrieve().body(FakeStoreProductDto[].class);
        return java.util.Arrays.stream(fakeStoreProductDtos).map(this::from).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Product createProduct(Product product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                client.post().uri("/products"). body(product)
                        .retrieve().toEntity(FakeStoreProductDto.class);

        if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                client.put().uri("/products/{id}", id). body(input)
        .retrieve().toEntity(FakeStoreProductDto.class);

        if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        client.delete().uri("/products/{id}", id).retrieve().toBodilessEntity();
    }

    private Boolean validateFakeStoreResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity) {
        return fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200));
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return  product;
    }
}
