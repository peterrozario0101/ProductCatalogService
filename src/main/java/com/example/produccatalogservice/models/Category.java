package com.example.produccatalogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category extends BaseModel{
    private String title;
    private String decription;
    private List<Product> products;
}
