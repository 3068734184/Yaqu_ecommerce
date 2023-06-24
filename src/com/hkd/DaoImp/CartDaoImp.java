package com.hkd.DaoImp;

import com.hkd.Dao.CartDao;

import com.hkd.entity.Cart;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartDaoImp extends DataBase implements CartDao {
    @Override
    public ArrayList<Cart> selectCart(String productid) throws SQLException {
        String sql = "SELECT product.productid, product.`name`, product.descn, product.price FROM product WHERE productid = 1";
        return (ArrayList<Cart>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Cart.class));
    }

    @Override
    public ArrayList<Cart> getCartByProductid(String productid, String username) throws SQLException {
        String sql = "SELECT product.productid, product.`name`, product.descn, product.price, account.address FROM " +
                "product, account WHERE productid = ? AND username = ?";
        return (ArrayList<Cart>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Cart.class), productid,username);
    }
}
