package com.company.servlet;

/**
 * Created by didi on 2018/12/17.
 */

import com.company.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public RegisterServlet(){

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        UserDao user=new UserDao();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        PrintWriter out=response.getWriter();
        JsonObject json=new JsonObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int register=user.addUser(username,password,phone,email);
            if(register>0){
                json.addProperty("status",10000);
                json.addProperty("msg","register success!");
                out.print(json);
            }
            else{
                json.addProperty("status",10001);
                json.addProperty("msg","未知原因注册失败了，重新注册一次吧,可能用户名已存在");
                out.print(json);

            }



    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {

   doGet(request,response);

    }

}
