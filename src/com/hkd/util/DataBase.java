package com.hkd.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DataBase {
    public static DruidDataSource dataSource;

    static {
        //创建连接池
        dataSource = new DruidDataSource();
        //配置
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/yaqu_ecommerce");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public static QueryRunner getQueryRunner() {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner;
    }
}
