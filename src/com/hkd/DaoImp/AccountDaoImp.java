package com.hkd.DaoImp;

import com.hkd.Dao.AccountDao;
import com.hkd.entity.Account;
import com.hkd.util.DataBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDaoImp extends DataBase implements AccountDao {

    @Override
    public ArrayList<Account> selectAccount(String username) throws SQLException {
        String sql = "select * from account where username=?";
        return (ArrayList<Account>) DataBase.getQueryRunner().query(sql, new BeanListHandler<>(Account.class), username);
    }

    @Override
    public void insertAccount(String name, String email, String addr, String sex, double balance) throws SQLException {
        String sql = "insert into account values(null,?,?,?,?,?)";
        DataBase.getQueryRunner().update(sql, name, email, addr, sex, balance);
    }

    @Override
    public void updateAccountBalanceByUserid(int userid,double balance) throws SQLException {
        String sql = "UPDATE account SET balance=? WHERE userid=?";
        DataBase.getQueryRunner().update(sql, balance, userid);
    }
}
