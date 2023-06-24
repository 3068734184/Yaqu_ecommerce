package com.hkd.servlet;

import com.hkd.DaoImp.AccountDaoImp;
import com.hkd.DaoImp.SignonDaoImp;
import com.hkd.entity.Account;
import com.hkd.entity.Signon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/dologin")
public class DoLogin extends HttpServlet {
    public DoLogin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            SignonDaoImp sdi = new SignonDaoImp();
            AccountDaoImp adi = new AccountDaoImp();
            HttpSession session = req.getSession();
            ArrayList<Signon> slist = sdi.selectByNameAndPwd(username, password);
            ArrayList<Account> alist = adi.selectAccount(username);

            if (slist.size() >= 1 && alist.size() >= 1) {
                session.setAttribute("user", username);
                session.setAttribute("userid", alist.get(0).getUserid());
                session.setAttribute("address", alist.get(0).getAddress());
                session.setAttribute("login", "login");
                resp.sendRedirect("doindex");
            } else {
                session.setAttribute("errorinfo", "用户名或密码错误，请重新登录");
                resp.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
