package com.example.project.EcommerceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long pro_id;

    private String name;

    private String description;

    private int categoryId;

    private double price;

    private String imageName;
}
