package com.karsauyeung.springbootmall.controller;


import com.karsauyeung.springbootmall.constant.ProductCategory;
import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.dlto.RequestParameter;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController  {
    @Autowired
    private ProductService productService ;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam (required = false) ProductCategory category ,
                                                     @RequestParam (required = false) String search ,
                                                     @RequestParam (defaultValue = "created_date") String order_by ,
                                                     @RequestParam (defaultValue = "DESC") String sort ,
                                                     @RequestParam (defaultValue = "5") @Min(0) @Max(100)Integer limit ,
                                                     @RequestParam (defaultValue = "0") @Min(0) Integer offset
    ){
        RequestParameter requestParameter  = new RequestParameter() ;
        requestParameter.setSearch(search);
        requestParameter.setCategory(category);
        requestParameter.setSort(sort);
        requestParameter.setOrder_by(order_by);
        requestParameter.setLimit(limit);
        requestParameter.setOffset(offset);

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(requestParameter)) ;
    }

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

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest) ;

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{$productID}")
    public ResponseEntity<Product> updateProductById(@PathVariable Integer productID ,
                                                     @RequestBody @Valid ProductRequest productRequest) {

        // check whether product exists or not

        Product product = productService.getProductById(productID);

        if (productID == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        productService.updateProductById(productID , productRequest) ;

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productID));
    }

    @DeleteMapping("/products/{productID}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer productID){

        productService.deleteProductById(productID) ;
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
