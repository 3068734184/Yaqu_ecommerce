package com.hkd.Dao;

import com.hkd.entity.Cart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CartDao {
    ArrayList<Cart> selectCart(String productid) throws SQLException;
    ArrayList<Cart> getCartByProductid(String productid, String username) throws SQLException;
}
