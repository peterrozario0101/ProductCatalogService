package com.example.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String imageUrl;
    private String description;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String isSaleSpecific;
    private String sku; // stock keeping unit, unique identifier
    private Integer stockQuantity;

}
