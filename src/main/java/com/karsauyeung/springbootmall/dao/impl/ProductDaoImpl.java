package com.karsauyeung.springbootmall.dao.impl;

import com.karsauyeung.springbootmall.dao.ProductDao;
import com.karsauyeung.springbootmall.dlto.ProductRequest;
import com.karsauyeung.springbootmall.dlto.RequestParameter;
import com.karsauyeung.springbootmall.model.Product;
import com.karsauyeung.springbootmall.rowmapper.ProductRowMapper;
import com.karsauyeung.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    @Override
    public Integer getCounts(RequestParameter requestParameter) {
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1" ;

        //filtering 條件
        Map<String , Object > map = new HashMap<>() ;
        if (requestParameter.getCategory()!=null){
            sql += " AND category = :category" ;
            map.put("category" , requestParameter.getCategory().name()) ;

        }
        if (requestParameter.getSearch() != null) {
            sql += " AND product_name LIKE :search";
            map.put("search" , "%" + requestParameter.getSearch() +"%") ;
        }

        Integer count = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return count ;

    }

    @Override
    public List<Product> getProducts(RequestParameter requestParameter) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                    "FROM product where 1=1" ;
        Map<String , Object > map = new HashMap<>() ;
        if (requestParameter.getCategory()!=null){
            sql += " AND category = :category" ;
            map.put("category" , requestParameter.getCategory().name()) ;

        }
        if (requestParameter.getSearch() != null) {
            sql += " AND product_name LIKE :search";
            map.put("search" , "%" + requestParameter.getSearch() +"%") ;
        }

            sql += " ORDER BY " +requestParameter.getOrder_by() +" "+ requestParameter.getSort() ;

            sql+=" LIMIT "+ requestParameter.getLimit() + " OFFSET "+requestParameter.getOffset() ;



        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        return productList ;
    }

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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
            String sql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                    "description, created_date, last_modified_date) " +
                    "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                    ":createdDate, :lastModifiedDate)";

            Map<String, Object> map = new HashMap<>();
            map.put("productName", productRequest.getProduct_name());
            map.put("category", productRequest.getCategory().toString()); // convert enum type into string for storage
            map.put("imageUrl", productRequest.getImage_url());
            map.put("price", productRequest.getPrice());
            map.put("stock", productRequest.getStock());
            map.put("description", productRequest.getDescription());

            Date now = new Date();
            map.put("createdDate", now);
            map.put("lastModifiedDate", now);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

            int productId = keyHolder.getKey().intValue();

            return productId;
        }

    @Override
    public void updateProductById(Integer productID, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, " +
                "price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productID);
        map.put("productName", productRequest.getProduct_name());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        // Usually, the last modified date is set to the current time during an update
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productID) {
        String sql = "DELETE FROM products where product_id = :productID" ;

        Map<String , Integer> map = new HashMap<>();
        map.put("productID" , productID) ;
        namedParameterJdbcTemplate.update(sql , map) ;

    }

}
