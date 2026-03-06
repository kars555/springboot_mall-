package com.karsauyeung.springbootmall.dao.impl;

import com.karsauyeung.springbootmall.dao.ProductDao;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    @Override
    public Product getProductById(Integer id) {

        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                      "FROM product " +
                       "WHERE product_id = :productId";

        Map<String , Object > map = new HashMap<>() ;
        map.put("productId" , id) ;

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        if (productList.size() > 0){
            return productList.get(0) ;
        }
        else {return null ; }

    }
}
