package com.hkd.servlet;

import com.hkd.DaoImp.AccountDaoImp;
import com.hkd.DaoImp.OrderDaoImp;
import com.hkd.entity.Cart;
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

@WebServlet("/doorder")
public class DoOrder extends HttpServlet {
    public DoOrder() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 获取订单的总金额
        double totalAmount = (double) session.getAttribute("total");
        // 获取用户余额
        double userBalance = Double.parseDouble((String) session.getAttribute("balance"));
        if (userBalance - totalAmount <= 0) {
            req.setAttribute("insufficientBalance", true);
            req.getRequestDispatcher("orderNew.jsp").forward(req, resp);
        } else {
            //获取当前用户id
            String userId = (String) session.getAttribute("userid");
            int userid = Integer.parseInt(userId);
//        System.out.println("整形userid:"+userid);
            // 获取用户下单的商品列表信息
            ArrayList<Cart> products = (ArrayList<Cart>) session.getAttribute("cartlist");


            for (Cart product : products) {

                Order order = new Order();
                order.setUserid(Integer.parseInt(userId));
                order.setProductid(product.getProductid());
                order.setName(product.getName());
                order.setDescn(product.getDescn());
                order.setPrice(product.getPrice());
                order.setNum(product.getPurchasenum());
                order.setTotalprice(product.getPrice() * product.getPurchasenum());
                order.setAddress(product.getAddress());

                // 调用OrderDaoImp中的insertOrder方法将订单插入到数据库的order表中
                OrderDaoImp odi = new OrderDaoImp();
                try {
                    odi.insertOrder(order);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


            // 计算订单的总金额
//        double totalAmount = order.getTotalprice();


            // 更新用户余额
            double updatedBalance = userBalance - totalAmount;
            AccountDaoImp adi = new AccountDaoImp();
            try {
                adi.updateAccountBalanceByUserid(userid, updatedBalance);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //清空购物车
            session.setAttribute("cartlist", null);
            session.setAttribute("total", 0);

            // 跳转到订单成功页面或其他相关页面
            resp.sendRedirect("orderHistory.jsp");
        }


    }
}
