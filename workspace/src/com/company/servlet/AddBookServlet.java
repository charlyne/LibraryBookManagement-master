package com.company.servlet;

import com.company.dao.BookDao;
import com.company.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by didi on 2019/1/3.
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        BookDao book=new BookDao();
        UserDao user=new UserDao();
        JsonObject json=new JsonObject();
        PrintWriter out=resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String username=(String)session.getAttribute("username");
        String password=(String)session.getAttribute("password");
        if(user.logIn(username,password)==1){
            String bookname=req.getParameter("bookname");
            String author=req.getParameter("author");
            String nn=req.getParameter("num");
            int num=Integer.parseInt(nn)>0?Integer.parseInt(nn):1;
            String press=(String)req.getParameter("press");
            String type=req.getParameter("type");
            book.addBook(bookname,author,num,press,type);
            json.addProperty("status",10000);
            json.addProperty("msg","add book success!");
            out.print(json);
        }else{

            json.addProperty("status",10001);
            json.addProperty("msg","add book failed for unknown reason");
            out.print(json);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
