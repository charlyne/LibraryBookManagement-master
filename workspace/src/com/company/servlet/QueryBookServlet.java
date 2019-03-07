package com.company.servlet;

/**
 * Created by didi on 2018/12/23.
 */
import com.company.bean.BookBean;
import com.company.dao.BookDao;
import com.company.utils.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/QueryBookServlet")
public class QueryBookServlet extends HttpServlet {
    public QueryBookServlet(){

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        HttpSession session=request.getSession(true);
        BookDao bookdao=new BookDao();
        ArrayList<BookBean> booklist=bookdao.queryBooks(request.getParameter("bookname"));
        response.setContentType("application/json");//这一句不要忘了
        response.setCharacterEncoding("UTF-8");
        //session.setMaxInactiveInterval(60);//大傻叉！！这里60是指一分钟，被这个问题搞了有一个月啦，session莫名奇妙的一两分钟就过期
        Gson gson=new Gson();
        String json=gson.toJson(booklist);
        PrintWriter out=response.getWriter();
        out.println(json);

    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
