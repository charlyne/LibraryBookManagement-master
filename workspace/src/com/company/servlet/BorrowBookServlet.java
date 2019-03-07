package com.company.servlet;

import com.company.bean.BookBean;
import com.company.dao.BookDao;
import com.company.dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by didi on 2018/12/23.
 */
@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        String username=(String)request.getSession().getAttribute("username");
        String bookname=request.getParameter("bookname");
        UserDao user=new UserDao();
        int num=user.borrowBook(username,bookname);
        PrintWriter out=response.getWriter();
        if(num>0){
            out.print("borrow succeed");
        }else{
            out.print("borrow failed");
        }
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doGet(request,response);
    }
}
