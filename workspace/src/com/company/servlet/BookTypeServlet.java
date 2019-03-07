package com.company.servlet;

import com.company.dao.BookDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by didi on 2019/1/3.
 */
@WebServlet("/BookTypeServlet")
public class BookTypeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao book=new BookDao();
        ArrayList<String> typelist=book.getAllBookType();
        Gson gson=new Gson();
        //JsonObject json=new JsonObject();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(typelist.size()>0){
           // json.addProperty("status",10000);
            //json.addProperty("msg","success");
            //json.addProperty("data",gson.toJson(typelist));
            map.put("status",10000);
            map.put("msg","success");
            map.put("data",typelist);

        }else{
            map.put("status",10001);
            map.put("msg","failed");
            map.put("data","");
        }
        PrintWriter out=resp.getWriter();
        out.print(gson.toJson(map));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
