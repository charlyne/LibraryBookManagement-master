package com.company.servlet;

import com.company.dao.BookDao;
import com.company.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

/**
 * Created by didi on 2018/12/23.
 */
@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
       // String username=request.getParameter("username");
        String bookname=request.getParameter("bookname");
        String username=(String) request.getSession().getAttribute("username");
        UserDao user=new UserDao();
        int numm=user.returnBook(username,bookname);
        PrintWriter out=response.getWriter();
        if(numm>0){
            out.print("return book succeed");
        }else{
            out.print("return book failed");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doGet(request, response);
    }
}
