package com.example.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private State state;

    public BaseModel() {
        createdAt = new Date();
        updatedAt = new Date();
        state = State.ACTIVE;
    }
}
