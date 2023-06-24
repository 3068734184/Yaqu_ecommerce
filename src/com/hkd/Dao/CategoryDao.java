package com.hkd.Dao;

import com.hkd.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDao {
    ArrayList<Category> getCategory() throws SQLException;

    void insertCategory(String catid, String catname, String catdesc) throws SQLException;
}
