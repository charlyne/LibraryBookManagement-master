<%--
  Created by IntelliJ IDEA.
  User: didi
  Date: 2018/12/24
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读者登录成功</title>
    <style>
        table {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
        button:hover{
            background-color: #4CAF50;
        }
    </style>
</head>
<script type="text/javascript" src="/book/static/jquery-3.1.1.min.js"></script>
<script>

    $(document).ready(function(){
        $(document).on("click","#bhisbtn",function(){
            $.get("/book/BorrowHistoryServlet",function(data){
                if(data.status!=null&&data.status==10000){
                    console.log('i am here')
                    window.location.href=data.url;
                }else{
                    $("#booktable").html("");
                    if(data.length>0){
                        var tr1=$("<tr></tr>");
                        tr1.append($("<th></th>").text("bno"));
                        tr1.append($("<th></th>").text("name"));
                        tr1.append($("<th></th>").text("author"));
                        tr1.append($("<th></th>").text("press"));
                        tr1.append($("<th></th>").text("type"));
                        tr1.append($("<th></th>").text("returnTime"));
                        tr1.append($("<th></th>").text("is_return"));
                        tr1.append($("<th></th>").text("operation"));

                        $("#booktable").append(tr1);
                        for(var i=0;i<data.length;i++){
                            var child1=$("<td></td>").text(data[i].bno);
                            var child2=$("<td class='bname'></td>").text(data[i].name);
                            var child3=$("<td></td>").text(data[i].author);
                            var child4=$("<td></td>").text(data[i].press);
                            var child5=$("<td></td>").text(data[i].type);
                            var child6=$("<td></td>").text(data[i].should_return_time);
                            if(data[i].is_return==1){
                                var child7=$("<td></td>").text("already returned");
                                var child8=$("<td></td>").text("");
                            }
                            else{
                                var child7=$("<td></td>").text("not returned");
                                var child8=$("<td></td>").append($("<button id='returnbutton'></button>").text("还书"));
                            }
                            var t=$("<tr></tr>");
                            t.append(child1);
                            t.append(child2);
                            t.append(child3);
                            t.append(child4);
                            t.append(child5);
                            t.append(child6);
                            t.append(child7);
                            t.append(child8);
                            $("#booktable").append(t);
                        }
                    }
                    else{
                        alert("查询不到您的借阅历史，可能您还没有借书");
                    }


                }

            })
        })
        $(document).on("click","#returnbutton",function(event){

            var bdata= $(event.target).parent().parent().children(".bname").text();
            $.ajax({
                url:"/book/ReturnBookServlet",
                data:{bookname:bdata},
                type:"GET",
                success:function(data){
                    if(data.status!=null&&data.status==10000){
                        window.location.href=data.url;
                    }else{
                        $(event.target).parent().parent().children().eq(5).text("already return");
                        $(event.target).parent().parent().children().eq(6).html("");
                    }

                }
            })

        })
        $(document).on("click","#querybtn",function(){
            var bookname=$("#bookname").val();
            $.ajax({
                url:"/book/QueryBookServlet",
                data:{bookname:bookname},
                type:"GET",
                dataType:"json",
                success:function(data){
                    console.log(data.status);
                    if(data.status!=null&&data.status==10000){
                        window.location.href=data.url;
                    }
                    else{
                        $("#booktable").html("");
                        if(data.length>0){
                            var tr1=$("<tr></tr>");
                            tr1.append($("<th></th>").text("bno"));
                            tr1.append($("<th></th>").text("name"));
                            tr1.append($("<th></th>").text("author"));
                            tr1.append($("<th></th>").text("press"));
                            tr1.append($("<th></th>").text("type"));
                            tr1.append($("<th></th>").text("operation"));
                            $("#booktable").append(tr1);
                            for(var i=0;i<data.length;i++){
                                var child1=$("<td></td>").text(data[i].bno);
                                var child2=$("<td class='bname'></td>").text(data[i].name);
                                var child3=$("<td></td>").text(data[i].author);
                                var child4=$("<td></td>").text(data[i].press);
                                var child5=$("<td></td>").text(data[i].type);
                                var child6=$("<td></td>").append($("<button id='borrowbtn'></button>").text("借书"));
                                var t=$("<tr></tr>");
                                t.append(child1);
                                t.append(child2);
                                t.append(child3);
                                t.append(child4);
                                t.append(child5);
                                t.append(child6);
                                $("#booktable").append(t);
                            }
                        }else{
                            alert("查询不到符合条件的书籍");
                        }


                    }

                }
            })
        })
        $(document).on("click","#borrowbtn",function(event){
            //event是点击目标
            var bdata= $(event.target).parent().parent().children(".bname").text();
            $.ajax({
                url:"/book/BorrowBookServlet",
                data:{bookname:bdata},
                type:"GET",
                success:function(data){
                    if(data.status!=null&&data.status==10000){
                        console.log('i am here')
                        window.location.href=data.url;
                        console.log(data.url);
                    }else{
                        //bug:这里不能使用borrowbtn，应该使用event才对
                        $(event.target).parent().parent().children().eq(5).text("borrow succeed");
                    }
                }
            })
        })
        $(document).on("click","#logout",function(){
            var r=confirm("您确定要退出登录吗？");
            if(r==true){
                $.ajax({
                    url:"/book/LogOutServlet",
                    type:"Get",
                    success:function(data){
                        window.location.href="http://localhost:8080/book/index.jsp";
                    }
                })
            }

        })

    })


</script>
<body>
<p style="margin-left: 60px">
    欢迎你，
    <%String name=(String)session.getAttribute("username");%>
    <%=name%>
    <button id="logout">退出</button>
    <button id="modify_password">修改密码</button>
</p>
<div style="margin-left: 100px; margin-top: 40px; " >

    <button id="querybtn">查询书籍</button>
    输入书名：<input type="text" name="bookname" id="bookname">
    <p></p>
    <button type="button" id="bhisbtn">借阅历史</button>

    <p>-----------------------------------------------------</p>
</div>
<table id="booktable">
</table>
</body>
</html>
