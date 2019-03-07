package com.company.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;


/**
 * Created by didi on 2019/3/1.
 */
@WebServlet("/Test1Servlet")
public class Test1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        System.out.println(this.getClass().getName());
        if(session==null){
            System.out.println("session is null");
            //session.setAttribute("username","1");
        }else{
            System.out.println("session username="+session.getAttribute("username"));
        }

        System.out.println("****************");
       // RequestDispatcher requestDispatcher=request.getRequestDispatcher("/book/Test1Servlet");
        //requestDispatcher.forward(request,response);
    }
}
