package com.hkd.servlet;

import com.hkd.DaoImp.OrderDaoImp;
import com.hkd.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/doorderhistory")
public class DoOrderHistory extends HttpServlet {
    public DoOrderHistory() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //获取当前用户id
        String userId = (String) session.getAttribute("userid");
        if("login".equals(session.getAttribute("login"))){
            OrderDaoImp odi = new OrderDaoImp();
            try {
                ArrayList<Order> orderlist = odi.getOrderListByUser(userId);
                session.setAttribute("orderlist", orderlist);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("orderHistory.jsp");
        }else {
            resp.sendRedirect("login.jsp");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
