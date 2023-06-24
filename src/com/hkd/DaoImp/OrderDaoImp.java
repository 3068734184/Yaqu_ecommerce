package com.hkd.DaoImp;

import com.hkd.Dao.OrderDao;
import com.hkd.entity.Order;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImp extends DataBase implements OrderDao {
    @Override
    public void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO `order` VALUES(null,?,?,?,?,?,?,?,?,NOW())";
        DataBase.getQueryRunner().update(sql, order.getUserid(), order.getProductid(), order.getName(), order.getDescn(), order.getPrice(),
                order.getNum(), order.getTotalprice(), order.getAddress());
    }

    @Override
    public ArrayList<Order> getOrderListByUser(String userid) throws SQLException {
        String sql = "SELECT * FROM `order` WHERE `userid` =?";
        return (ArrayList<Order>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Order.class), userid);
    }
}
