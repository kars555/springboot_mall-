package com.karsauyeung.springbootmall.service.impl;

import com.karsauyeung.springbootmall.dao.ProductDao;
import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.dlto.RequestParameter;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao ;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    public void updateProductById(Integer productID ,ProductRequest productRequest){
         productDao.updateProductById(productID , productRequest);
    }

    @Override
    public void deleteProductById(Integer productID) {
        productDao.deleteProductById(productID) ;
    }

    @Override
    public List<Product> getProducts(RequestParameter requestParameter) {
        return productDao.getProducts(requestParameter);
    }
}
