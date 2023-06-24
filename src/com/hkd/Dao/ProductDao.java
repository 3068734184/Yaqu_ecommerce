package com.hkd.Dao;

import com.hkd.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDao {
    ArrayList<Product> getProduct() throws SQLException;

    ArrayList<Product> getProductByProductId(String productid) throws SQLException;

//    void updateProduct(Product productid) throws SQLException;

    ArrayList<Product> getProductByCategory(String category) throws SQLException;

    ArrayList<Product> getProductByNameAndPage(String name, int page) throws SQLException;

    ArrayList<Product> getProductByNameAndPage(String name, int page, int pageSize) throws SQLException;

    int getCount(String category) throws SQLException;

    int getCountByName(String name) throws SQLException;
}
