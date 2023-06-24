package com.hkd.Dao;

import com.hkd.entity.Signon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SignonDao {
//    ArrayList<Signon> getAllSignon() throws SQLException;

    ArrayList<Signon> selectByName(String username) throws SQLException;

    ArrayList<Signon> selectByNameAndPwd(String username, String password) throws SQLException;

    void insertSignon(String username, String password) throws SQLException;

//    void deleteSignon(String username) throws SQLException;

//    boolean checkUnameAndPwd(String username, String password) throws SQLException;
}
