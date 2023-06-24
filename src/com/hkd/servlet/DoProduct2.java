package com.hkd.servlet;

import com.hkd.DaoImp.ProductDaoImp;
import com.hkd.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/doproduct2")
public class DoProduct2 extends HttpServlet {
    public DoProduct2() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catid = null;
        ProductDaoImp pdi = new ProductDaoImp();
        HttpSession session = req.getSession();
        if (session.getAttribute("catid") == null || req.getParameter("cid") != null) {
            catid = req.getParameter("cid");
        } else catid = (String) session.getAttribute("catid");


        int count = 0;
        try {
            count = pdi.getCount(catid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int pageCount = 0;
        if (count % 5 == 0) {
            pageCount = count / 5;
        } else pageCount = count / 5 + 1;

        int pageGroupCount = 0;
        if (pageCount % 5 == 0) {
            pageGroupCount = pageCount / 5;
        } else pageGroupCount = pageCount / 5 + 1;

        int pageNo = 0;
        String str = req.getParameter("pageNo");
        if (str != null)
            pageNo = Integer.parseInt(str);
        if (session.getAttribute("pageNo") == null)
            pageNo = 1;
        else if (req.getParameter("pageNo") == null)
            pageNo = (int) session.getAttribute("pageNo");

        int pageGroupNo = 0;
        if (session.getAttribute("pageGroupNo") == null)
            pageGroupNo = 0;
        else pageGroupNo = (int) session.getAttribute("pageGroupNo");

        String flag = req.getParameter("flag");
        if ("up".equals(flag)) {
            if (pageGroupNo < pageGroupCount - 1) pageGroupNo++;
            else pageGroupNo = pageGroupCount - 1;
        } else if ("down".equals(flag)) {
            if (pageGroupNo >= 1) pageGroupNo--;
            else pageGroupNo = 0;
        }

        ArrayList<ArrayList> clist = new ArrayList<>();
        for (int i = 1; i <= pageCount; i += 5) {
            ArrayList<Integer> tlist = new ArrayList<Integer>();
            for (int j = i; j <= i + 4; j++) {
                if (j <= pageCount)
                    tlist.add(j);
            }
            clist.add(tlist);
        }
        System.out.println(pageGroupNo);
        session.setAttribute("pageGroupNum", clist.get(pageGroupNo));
        session.setAttribute("pageGroupNo", pageGroupNo);
        session.setAttribute("pageNo", pageNo);

        ArrayList<Product> plist = null;
        try {
            plist = pdi.getProductByNameAndPage(catid, pageNo, 5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("plist", plist);
        session.setAttribute("catid", catid);
        resp.sendRedirect("product2.jsp");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}