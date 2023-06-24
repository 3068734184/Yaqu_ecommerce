package com.hkd.DaoImp;

import com.hkd.Dao.SignonDao;
import com.hkd.entity.Signon;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignonDaoImp extends DataBase implements SignonDao {
    @Override
    public ArrayList<Signon> selectByName(String username) throws SQLException {
        String sql = "select * from signon where username =?";
        return (ArrayList<Signon>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Signon.class),username);
    }

    @Override
    public ArrayList<Signon> selectByNameAndPwd(String username, String password) throws SQLException {
        String sql = "select * from signon where username =? and password =?";
        return (ArrayList<Signon>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Signon.class),username,password);
    }

    @Override
    public void insertSignon(String username, String password) throws SQLException {
        String sql = "insert into signon(username,password) values(?,?)";
        DataBase.getQueryRunner().update(sql,username,password);
    }
}
