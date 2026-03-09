package com.karsauyeung.springbootmall.service;

import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.model.Product;

public interface ProductService {
     Product getProductById(Integer id);
     Integer createProduct(ProductRequest productRequest) ;
     void updateProductById(Integer productID ,ProductRequest productRequest);
     void deleteProductById(Integer productID ) ;
}
