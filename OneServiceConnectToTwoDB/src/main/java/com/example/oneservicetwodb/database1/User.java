package com.example.oneservicetwodb.database1;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

}
