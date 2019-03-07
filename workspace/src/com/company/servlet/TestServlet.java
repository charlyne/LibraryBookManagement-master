package com.company.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by didi on 2019/3/1.
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        System.out.println(this.getClass().getName());
        if(session==null){
            System.out.println("session is null");
            session=request.getSession(true);
            session.setAttribute("username","1");

        }else{
            System.out.println("session username="+session.getAttribute("username"));
            session.setAttribute("username","1");
        }
        System.out.println("****************");
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Test1Servlet");
        requestDispatcher.forward(request,response);

    }
}
