package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    Long id;
    String title;
    String description;
    String category;
    String image;
    Double price;
}

