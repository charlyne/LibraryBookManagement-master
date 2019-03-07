package com.company.filter;

/**
 * Created by didi on 2018/12/27.
 */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class SessionFilter implements Filter{
    private String excludedPage;
    private String[] excludedPages;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPage=filterConfig.getInitParameter("ignoneServlet");
        if(excludedPage!=null&&excludedPage.length()>0){
            excludedPages=excludedPage.split(",");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)throws IOException,ServletException{
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;
        boolean flag=false;
        Cookie[] cookies=request.getCookies();
        System.out.println(request.getRequestURI());
        System.out.print("*******"+new Date()+"*****");
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+"="+cookie.getValue());
        }
        for(String page:excludedPages){
            if(request.getRequestURI().contains(page)){
                flag=true;
                break;
            }
        }
        if(flag){
            filterChain.doFilter(request,response);
        } else{
            HttpSession session=request.getSession(false);
            if(session==null||session.getAttribute("username")==null){
                //判断是否是ajax请求
                String requestedWith=request.getHeader("X-Requested-With");
                if(requestedWith!=null&&requestedWith.equalsIgnoreCase("XMLHttpRequest")){
                    response.setContentType("application/json");//这一句不要忘了
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("REDIRECT","REDIRECT");
                    response.addHeader("CONTENTPATH","http://localhost:8080/book/index.jsp");

                }else{
                    //如果不是ajax请求
                    response.sendRedirect("/book/index.jsp");
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }

    }


}
