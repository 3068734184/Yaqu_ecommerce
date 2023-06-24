package com.hkd.servlet;


import com.hkd.DaoImp.CartDaoImp;
import com.hkd.entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/docart")
public class DoCart extends HttpServlet {
    public DoCart() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String productid = req.getParameter("productid");

        String username = (String) session.getAttribute("user");

        CartDaoImp cdi = new CartDaoImp();

        try {
            ArrayList<Cart> list = cdi.getCartByProductid(productid, username);
            Cart cii = list.get(0);

            ArrayList<Cart> cartlist = new ArrayList<>();

            boolean flag = false;

            if (session.getAttribute("cartlist") == null) {
                cii.setPurchasenum(1);
                cartlist.add(cii);
            } else {
                cartlist = (ArrayList<Cart>) session.getAttribute("cartlist");
                for (Cart c : cartlist) {
                    double total = 0;
                    if (c.getProductid().equals(cii.getProductid())) {
                        flag = true;
                        c.setPurchasenum(c.getPurchasenum() + 1);
                        total = total + c.getPurchasenum() * c.getPrice();
                        session.setAttribute("purchasenum", c.getPurchasenum());
                    }
                    session.setAttribute("total", total);
                }
                if (!flag) {
                    cii.setPurchasenum(1);
                    cartlist.add(cii);
                }
            }
            session.setAttribute("cartlist", cartlist);
            resp.sendRedirect("cart.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
