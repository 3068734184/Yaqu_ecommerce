package com.hkd.Dao;

import com.hkd.entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountDao {
    ArrayList<Account> selectAccount(String username) throws SQLException;

    void insertAccount(String name, String email, String address, String sex,double balance) throws SQLException;

    void updateAccountBalanceByUserid(int userid,double balance) throws SQLException;
}
