package com.api.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue
    private Integer product_id;
    private String name;
    private Double price;

}
