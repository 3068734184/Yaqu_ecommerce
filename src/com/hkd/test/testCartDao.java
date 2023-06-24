package com.hkd.test;

import com.hkd.Dao.CartDao;
import com.hkd.DaoImp.CartDaoImp;
import com.hkd.entity.Cart;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class testCartDao {
    @Test
    public void testGetCartByProductid() throws SQLException {
        CartDaoImp cdi = new CartDaoImp();
        ArrayList<Cart> cart = cdi.getCartByProductid("1", "lisi");
        System.out.println(cart);
        System.out.println(cart.get(0).getName());
        System.out.println(cart.get(0).getDescn());
    }
}
