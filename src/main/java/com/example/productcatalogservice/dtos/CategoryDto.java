package com.example.productcatalogservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {
    private Long id;
    private String title;
    private String description;
}
