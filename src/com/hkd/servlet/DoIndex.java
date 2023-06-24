package com.hkd.servlet;

import com.hkd.DaoImp.CategoryDaoImp;
import com.hkd.entity.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/doindex")
public class DoIndex extends HttpServlet {
    public DoIndex() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CategoryDaoImp cdi=new CategoryDaoImp();
            ArrayList<Category> slist=cdi.getCategory();
            HttpSession session=req.getSession();
            session.setAttribute("slist",slist);
            resp.sendRedirect("index2.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
