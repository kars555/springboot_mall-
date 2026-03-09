package com.karsauyeung.springbootmall.service;

import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.model.Product;

public interface ProductService {
    public Product getProductById(Integer id);
    public Integer createProduct(ProductRequest productRequest) ;
}
