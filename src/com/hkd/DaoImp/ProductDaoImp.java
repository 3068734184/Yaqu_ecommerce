package com.hkd.DaoImp;

import com.hkd.Dao.ProductDao;
import com.hkd.entity.Product;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDaoImp extends DataBase implements ProductDao {
    @Override
    public ArrayList<Product> getProduct() throws SQLException {
        String sql = "select * from product";
        return (ArrayList<Product>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public ArrayList<Product> getProductByProductId(String productid) throws SQLException {
        String sql = "select * from product where productid=?";
        return (ArrayList<Product>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Product.class),productid);
    }

    @Override
    public ArrayList<Product> getProductByCategory(String category) throws SQLException {
        String sql = "select * from product where category=?";
        return (ArrayList<Product>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Product.class),category);
    }

    @Override
    public ArrayList<Product> getProductByNameAndPage(String name, int page) throws SQLException {
        String sql = "select * from product where name like '%" + name + "%' limit " + (page - 1) * 5 + ",5";
        return (ArrayList<Product>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public ArrayList<Product> getProductByNameAndPage(String name, int page, int pageSize) throws SQLException {
        String sql = "select * from product where category='" + name + "' limit " + (page - 1) * pageSize + "," + pageSize + "";
        return (ArrayList<Product>) DataBase.getQueryRunner().query(sql,new BeanListHandler<>(Product.class));
    }

    @Override
    public int getCount(String category) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE category=?";
        return DataBase.getQueryRunner().query(sql, new ScalarHandler<Long>(), category).intValue();
    }

    @Override
    public int getCountByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE name LIKE ?";
        return DataBase.getQueryRunner().query(sql, new ScalarHandler<Long>(), "%" + name + "%").intValue();
    }
}
