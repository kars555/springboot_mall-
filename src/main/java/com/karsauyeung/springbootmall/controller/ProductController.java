package com.karsauyeung.springbootmall.controller;


import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController  {
    @Autowired
    private ProductService productService ;

    @GetMapping("/products/{productID}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productID){
        Product product = productService.getProductById(productID);

        System.out.println(product);
        if (product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
