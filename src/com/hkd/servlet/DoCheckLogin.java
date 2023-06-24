package com.hkd.servlet;

import com.hkd.DaoImp.AccountDaoImp;
import com.hkd.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/dochecklogin")
public class DoCheckLogin extends HttpServlet {
    public DoCheckLogin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);


        if("login".equals(session.getAttribute("login")))
        {
            AccountDaoImp asi=new AccountDaoImp();

            String user=(String) session.getAttribute("user");

            ArrayList<Account> list= null;
            try {
                list = asi.selectAccount(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            String email = null;
            String address = null;

            String balance = null;

            for(Account a:list)
            {

                email = a.getEmail();
                address = a.getAddress();

                balance = a.getBalance();
            }
            session.setAttribute("user", user);
            session.setAttribute("email", email);
            session.setAttribute("address", address);

            session.setAttribute("balance",balance);

            resp.sendRedirect("orderNew.jsp");
        }
        else
            resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
