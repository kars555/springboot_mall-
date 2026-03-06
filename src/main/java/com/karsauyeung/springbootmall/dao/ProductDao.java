package com.karsauyeung.springbootmall.dao;

import com.karsauyeung.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);
}
