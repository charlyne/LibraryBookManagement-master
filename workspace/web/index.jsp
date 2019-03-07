<%--
  Created by IntelliJ IDEA.
  User: didi
  Date: 2018/12/10
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎首页
    </title>
  </head>
  <style>
    button{
      background-color: #4CAF50;
      border: none;
      color: white;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 4px 2px;
      cursor: pointer;
    }
    div{
      width: 300px;
      height: 150px;
      align:"float";
      border:1px solid #330c1a;
      margin: 10px;
      padding: 20px;
    }
  </style>
  <script type="text/javascript" src="/book/static/jquery-3.1.1.min.js"></script>
  <script>
    $(document).ready(function(){
      $(document).on("click","#signup_commit",function(){
        var username=$("#signup_username").val();
        var password=$("#signup_pass").val();
        var phone=$("#signup_phone").val();
        var email=$("#signup_email").val();
        if(username==""||password==""||phone==""||email==""){
          alert("用户名，密码，联系方式，邮箱不能为空，请重写填写");
        }else{
          $.ajax({
            url:"/book/RegisterServlet",
            data:{username:username,password:password,phone:phone,email:email},
            type:"GET",
            dataType:"json",
            success:function(data){
              alert(data.msg);
              $("#register_form")[0].reset();
            }
          })
        }
      })
      /**$(document).on("click","#login_commit",function(){
        var username="admin";
        var password=$("#login_password").val();
        if(username=""||password==""){
          confirm("username and password cannot be empty.")
        }else{
          $.ajax({
            url:"/book/LoginServlet",
            type:"GET",
            dataType:"json",
            data:{username:"admin",password:password},
            success:function(data){
              if(data.status&&data.msg){
                alert(data.msg);
                window.location.reload();
              }
            }
          })
        }
      })
      */

    })
  </script>
  <body>
  <div>
    <form id="register_form">
      username: <input type="text" name="username" id="signup_username">
      <br>
      password:<input type="password" name="password" id="signup_pass">
      <br>
      phone:   <input type="tel" name="phone" id="signup_phone">
      <br>
      email:   <input type="email" name="email" id="signup_email">
      <br>
    </form>
    <button id="signup_commit">sign up now</button>
  </div>
  <div>

    <form action="/book/LoginServlet" method="get" accept-charset="utf-8">
      username: <input type="text" name="username" id="login_username">
      <br>
      password:<input type="text" name="password" id="login_password">
      <br>
      <button id="login_commit">sign in</button>
    </form>

  </div>

  </body>
</html>
