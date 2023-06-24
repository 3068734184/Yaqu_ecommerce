package com.hkd.Dao;

import com.hkd.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao {
    void insertOrder(Order order) throws SQLException;

    ArrayList<Order> getOrderListByUser(String user) throws SQLException;

}
