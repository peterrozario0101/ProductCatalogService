package com.example.produccatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String imageUrl;
    private String description;
    private double price;
    private Category category;
    private String isSaleSpecific;


}
