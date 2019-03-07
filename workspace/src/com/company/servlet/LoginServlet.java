package com.company.servlet;

/**
 * Created by didi on 2018/12/23.
 */
import com.company.dao.UserDao;
import com.company.utils.MySessionContext;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    String sid="";
   protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
       HttpSession session = request.getSession(true);
       //session.setMaxInactiveInterval(30*60);
       String username=request.getParameter("username");
       String password=request.getParameter("password");
       UserDao user=new UserDao();
       int role=user.logIn(username,password);
       PrintWriter out=response.getWriter();
       JsonObject json=new JsonObject();
       response.setContentType("application/json");
       response.setCharacterEncoding("UTF-8");

       if(role==2){
           session.setAttribute("username",username);
           session.setAttribute("password",password);
           response.sendRedirect("/book/readercontroller.jsp");

       }else{
           if(role==1){
               session.setAttribute("username",username);
               session.setAttribute("password",password);
               response.sendRedirect("/book/admincontroller.jsp");
           }
           else{
               json.addProperty("status",10002);
               json.addProperty("msg","please check your username and password,try again!");
               out.print(json);
           }
       }

   }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }

}
