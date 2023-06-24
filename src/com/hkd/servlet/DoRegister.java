package com.hkd.servlet;

import com.hkd.DaoImp.AccountDaoImp;
import com.hkd.DaoImp.SignonDaoImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import static com.hkd.util.DataBase.dataSource;

@WebServlet("/doregister")
public class DoRegister extends HttpServlet {
    public DoRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("addr");
        String sex = req.getParameter("sex");
        double balance= Double.parseDouble(req.getParameter("balance"));
        SignonDaoImp sdi = new SignonDaoImp();
        AccountDaoImp adi = new AccountDaoImp();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            sdi.insertSignon(username, password);
            adi.insertAccount(username, email, address, sex, balance);
            conn.commit();
//            out.print("<script>alert('right')</script>");
            out.print("<script>window.location.href='login.jsp'</script>");
        } catch (SQLException e) {
//            System.out.println("错误回滚");
            if (conn != null) {
                try {
                    conn.rollback();
                    out.print("<script>alert('wrong')</script>");
                    out.print("<script>window.location.href='register.jsp'</script>");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
