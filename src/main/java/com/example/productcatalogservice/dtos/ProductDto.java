package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private Double price;
    private CategoryDto category;

}
