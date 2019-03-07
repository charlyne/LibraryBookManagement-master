package com.company.servlet;

import com.company.bean.Book2Bean;
import com.company.bean.BookBean;
import com.company.dao.BookDao;
import com.company.dao.UserDao;
import com.company.utils.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by didi on 2018/12/24.
 */
@WebServlet("/BorrowHistoryServlet")
public class BorrowHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session=request.getSession(false);
        //session.setMaxInactiveInterval(60*60);
        String username=(String) session.getAttribute("username");
        BookDao bookdao=new BookDao();
        ArrayList<Book2Bean> booklist=null;
        try {
            booklist=bookdao.borrowHistory(username);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Gson gson=new Gson();
            String json=gson.toJson(booklist);
            PrintWriter out=response.getWriter();
            out.println(json);


            //request.setAttribute("booklist",booklist);
            //request.getRequestDispatcher("/borrowedbookrecord.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
