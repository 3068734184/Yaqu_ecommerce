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

@WebServlet("/doproduct")
public class DoProduct extends HttpServlet {
    public DoProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catid=null;
        HttpSession session=req.getSession();


        if(session.getAttribute("catid")==null||req.getParameter("cid")!=null)
            catid=req.getParameter("cid");
        else
            catid=(String) session.getAttribute("catid");

        ProductDaoImp pdi=new ProductDaoImp();

        int count= 0;
        try {
            count = pdi.getCount(catid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int pageCount=0;
        if(count%5==0)
            pageCount=count/5;
        else
            pageCount=count/5+1;


        int pageNo=0;

        if(session.getAttribute("pageNo")==null)
        {
            pageNo=1;
        }
        else
        {
            pageNo=(int) session.getAttribute("pageNo");
        }
        String flag=req.getParameter("flag");
        if("up".equals(flag))
        {
            if(pageNo<pageCount)
                pageNo++;
            else
                pageNo=pageCount;
        }
        else if("down".equals(flag))
        {
            if(pageNo>1)
                pageNo--;
            else
                pageNo=1;
        }

        session.setAttribute("pageNo", pageNo);

        ArrayList<Product> plist= null;
        try {
            plist = pdi.getProductByNameAndPage(catid, pageNo, 5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("plist", plist);
        session.setAttribute("catid", catid);
        resp.sendRedirect("product.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
