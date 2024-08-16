package com.example.oneservicetwodb.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    IProductRepository repository;

    public List<ProductDTO> findAllProducts() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = repository.findAll();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(product.getId());
                productDTO.setName(product.getName());
                productDTO.setDescription(product.getDescription());
                productDTOS.add(productDTO);
            }
        }

        return productDTOS;
    }
}
