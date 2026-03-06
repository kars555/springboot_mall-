package com.karsauyeung.springbootmall.service.impl;

import com.karsauyeung.springbootmall.dao.ProductDao;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao ;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
