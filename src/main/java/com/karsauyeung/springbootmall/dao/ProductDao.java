package com.karsauyeung.springbootmall.dao;

import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.dlto.RequestParameter;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.util.Page;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProductById(Integer productID ,ProductRequest productRequest);
    void deleteProductById(Integer productID) ;
    List<Product> getProducts(RequestParameter requestParameter) ;
    Integer getCounts(RequestParameter requestParameter) ;
}
