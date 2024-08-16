package com.example.oneservicetwodb.database2;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
