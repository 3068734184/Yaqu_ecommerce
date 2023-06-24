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

@WebServlet("/doremovecart")
public class DoRemoveCart extends HttpServlet {
    public DoRemoveCart() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String productid = req.getParameter("productid");
        String username = (String) session.getAttribute("user");
        CartDaoImp cdi = new CartDaoImp();

        ArrayList<Cart> list;
        try {
            list = cdi.getCartByProductid(productid,username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Cart cii = list.get(0);

        ArrayList<Cart> cartlist = new ArrayList<>();
        cartlist = (ArrayList<Cart>) session.getAttribute("cartlist");
        for (Cart c : cartlist) {
            if (c.getProductid().equals(cii.getProductid())) {
                cartlist.remove(c);
                break;
            }
        }
        session.setAttribute("cartlist", cartlist);
        resp.sendRedirect("cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
