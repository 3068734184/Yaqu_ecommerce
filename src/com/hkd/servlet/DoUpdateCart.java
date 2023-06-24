package com.hkd.servlet;

import com.hkd.entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/doupdatecart")
public class DoUpdateCart extends HttpServlet {
    public DoUpdateCart() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if ("login".equals(session.getAttribute("login"))) {
            ArrayList<Cart> clist = (ArrayList<Cart>) session.getAttribute("cartlist");

            int i = 0;
            double sum = 0;
            double total = 0;
            if (clist != null) {
                for (Cart c : clist) {
                    //1.接收文本框中的数据（购买数量）
                    String qty = req.getParameter("purchasenum" + i);
                    int qty_int = Integer.parseInt(qty);

                    c.setPurchasenum(qty_int);
                    sum = c.getPurchasenum() * c.getPrice();    //每个商品的总价
                    total = total + sum;

                    i++;
                }

            }

            session.setAttribute("total", total);

            resp.sendRedirect("cart.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }

    }
}
