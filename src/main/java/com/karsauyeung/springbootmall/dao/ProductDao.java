package com.karsauyeung.springbootmall.dao;

import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProductById(Integer productID ,ProductRequest productRequest);
    void deleteProductById(Integer productID) ;
}
