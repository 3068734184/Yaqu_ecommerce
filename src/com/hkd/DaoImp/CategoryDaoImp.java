package com.hkd.DaoImp;

import com.hkd.Dao.CategoryDao;
import com.hkd.entity.Category;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDaoImp extends DataBase implements CategoryDao {
    @Override
    public ArrayList<Category> getCategory() throws SQLException {
        return (ArrayList<Category>) DataBase.getQueryRunner().query("select * from category",
                new BeanListHandler<>(Category.class));
    }

    @Override
    public void insertCategory(String catid, String catname, String catdesc) throws SQLException {
        String sql = "insert into category values (?,?,?)";
        DataBase.getQueryRunner().update(sql, catid, catname, catdesc);
    }
}
