package com.example.oneservicetwodb.controller;

import com.example.oneservicetwodb.database2.ProductDTO;
import com.example.oneservicetwodb.database2.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/list-all")
    public ResponseEntity<List<ProductDTO>> findAllProduct() {
        var result = service.findAllProducts();
        return ResponseEntity.ok(result);
    }

}
