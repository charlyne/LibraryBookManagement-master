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
    <title>管理员登录成功</title>
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
    var jqxhr;
    $.ajaxSetup({
        complete:function(){
            if("REDIRECT" == jqxhr.getResponseHeader("REDIRECT")){ //若HEADER中含有REDIRECT说明后端想重定向，
                var win = window;
                while(win != win.top){
                    win = win.top;
                }
                win.location.href = jqxhr.getResponseHeader("CONTENTPATH");//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            }
        }
    });



    $(document).ready(function(){
        $("#addbookform").hide();
        //禁止表单默认提交的行为
        $("#addbookform").submit(function(e){
            e.preventDefault();
        });
        $(document).on("click","#bhisbtn",function(){
            $("#addbookform").hide();
            jqxhr=$.get("/book/BorrowHistoryServlet",function(data){
                if(data.status!=null&&data.status==10000){
                    window.location.href=data.url;
                }else{
                    $("#booktable").html("");
                    if(data.length>0){
                        var tr1=$("<tr></tr>");
                        //tr1.append($("<th></th>").text("bno"));
                        tr1.append($("<th></th>").text("编号"));
                        tr1.append($("<th></th>").text("书名"));
                        tr1.append($("<th></th>").text("作者"));
                        tr1.append($("<th></th>").text("出版社"));
                        tr1.append($("<th></th>").text("类型"));
                        tr1.append($("<th></th>").text("还书时间"));
                        tr1.append($("<th></th>").text("是否还书"));
                        tr1.append($("<th></th>").text("操作"));

                        $("#booktable").append(tr1);
                        for(var i=0;i<data.length;i++){
                            //var child1=$("<td></td>").text(data[i].bno);
                            var child1=$("<td></td>").text(i+1);
                            var child2=$("<td class='bname'></td>").text(data[i].name);
                            var child3=$("<td></td>").text(data[i].author);
                            var child4=$("<td></td>").text(data[i].press);
                            var child5=$("<td></td>").text(data[i].type);
                            var child6=$("<td></td>").text(data[i].should_return_time);
                            if(data[i].is_return==1){
                                var child7=$("<td></td>").text("yes");
                                var child8=$("<td></td>").text("");
                            }
                            else{
                                var child7=$("<td></td>").text("no");
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
        });
        $(document).on("click","#returnbutton",function(event){
            var bdata= $(event.target).parent().parent().children(".bname").text();
            jqxhr=$.ajax({
                url:"/book/ReturnBookServlet",
                data:{bookname:bdata},
                type:"GET",
                success:function(data){
                    if(data.status!=null&&data.status==10000){
                        window.location.href=data.url;
                    }else{
                        $("#bhisbtn").click();
                       // $(event.target).parent().parent().children().eq(5).text("already return");
                       // $(event.target).parent().parent().children().eq(6).html("");
                    }

                }
            })

        });
        $(document).on("click","#querybtn",function(){
            $("#addbookform").hide();
            var bookname=$("#bookname").val();
            jqxhr=$.ajax({
                url:"/book/QueryBookServlet",
                data:{bookname:bookname},
                type:"GET",
                dataType:"json",
                success:function(data){
                    if(data.status!=null&&data.status==10000){
                        window.location.href=data.url;
                    }
                    else{
                        $("#booktable").html("");
                        if(data.length>0){
                            var tr1=$("<tr></tr>");
                            //tr1.append($("<th></th>").text("bno"));
                            tr1.append($("<th></th>").text("编号"));
                            tr1.append($("<th></th>").text("书名"));
                            tr1.append($("<th></th>").text("作者"));
                            tr1.append($("<th></th>").text("出版社"));
                            tr1.append($("<th></th>").text("类型"));
                            tr1.append($("<th></th>").text("在馆数量"));
                            tr1.append($("<th></th>").text("操作"));
                            $("#booktable").append(tr1);
                            for(var i=0;i<data.length;i++){
                                //var child1=$("<td></td>").text(data[i].bno);
                                var child1=$("<td></td>").text(i+1);
                                var child2=$("<td class='bname'></td>").text(data[i].name);
                                var child3=$("<td></td>").text(data[i].author);
                                var child4=$("<td></td>").text(data[i].press);
                                var child5=$("<td></td>").text(data[i].type);
                                var child6=$("<td></td>").text(data[i].num);
                                var child7;
                                if(data[i].num>0){
                                    child7=$("<td></td>").append($("<button id='borrowbtn'></button>").text("借书"));
                                }else{
                                    child7=$("<td></td>").text("不可借");
                                }
                                var t=$("<tr></tr>");
                                t.append(child1);
                                t.append(child2);
                                t.append(child3);
                                t.append(child4);
                                t.append(child5);
                                t.append(child6);
                                t.append(child7);
                                $("#booktable").append(t);
                            }
                        }else{
                            alert("查询不到符合条件的书籍");
                        }
                    }
                }
            })
        });
        $(document).on("click","#borrowbtn",function(event){
            //event是点击目标
            var bdata= $(event.target).parent().parent().children(".bname").text();
            jqxhr=$.ajax({
                url:"/book/BorrowBookServlet",
                data:{bookname:bdata},
                type:"GET",
                success:function(data){
                    if(data.status!=null&&data.status==10000){
                        window.location.href=data.url;
                    }else{
                        //bug:这里不能使用borrowbtn，应该使用event才对
                        $(event.target).parent().parent().children().eq(5).text(data);
                    }
                }
            })
        });
        $(document).on("click","#logout",function(){
            var r=confirm("退出后将返回登录页面，您确定要退出登录吗？");
            if(r==true){
                jqxhr=$.ajax({
                    url:"/book/LogOutServlet",
                    type:"Get",
                    success:function(data){
                        window.location.href="http://localhost:8080/book/index.jsp";
                    }
                })
            }

        });
            $(document).on("click","#showaddbook",function(){
                //获取数据库中书的所有类目

                jqxhr=$.ajax({
                    url:"/book/BookTypeServlet",
                    type:"Get",
                    dataType:"json",
                    success:function(data){
                        if(!data.status){
                            alert("an unknown error occured!")
                        }else{
                            if(data.status==10000&&data.data.length>0&&document.getElementById("btypeslt")!=null){
                                var selet=$("<select id='btypeslt'></select>");
                                for(var i=0;i<data.data.length;i++){
                                    var op=$("<option></option>");
                                    op.attr("value",data.data[i]);
                                    op.text(data.data[i]);
                                    selet.append(op);
                                }
                                $("#addbookform").append(selet);
                                $("#addbookform").show();
                            }else {
                                alert(data.msg);
                            }
                        }
                    }
                })
            });
        $(document).on("click","#addbookbtn",function(){
            var bookname=$("#add_bookname").val();
            var author=$("#add_author").val();
            var press=$("#add_press").val();
            var num=$("#add_num").val();
            var type=$("#btypeslt option:selected ").text();
            if(bookname==""||author==""||press==""||num==""||type==""){
                alert(" input cannot be null")
            }else{
                jqxhr=$.ajax({
                    url:"/book/AddBookServlet",
                    data:{bookname:bookname,author:author,press:press,num:num,type:type},
                    type:"Get",
                    dataType:"json",
                    success:function(data){
                        if(data.status>0){
                            alert(data.msg);
                        }
                    }

                })
            }


        });


            <%--查询书的类目，并且展示添加书的表格--%>

    })


</script>
<body>
<p style="margin-left: 60px">
    欢迎你，管理员
    <%String name=(String)session.getAttribute("username");%>
    <%=name%>
    <button id="logout">退出</button>
    <button id="modify_password">修改密码</button>
</p>
<div style="margin-left: 100px; margin-top: 40px;" id="maindiv" >

    <button id="querybtn">查询书籍</button>
    输入书名：<input type="text" name="bookname" id="bookname">
    <p></p>
    <button type="button" id="bhisbtn">借阅历史</button>
    <p></p>
    <button id="showaddbook" >添加书籍</button>

    <p>-----------------------------------------------------</p>
</div>
<table id="booktable">
</table>
<form id="addbookform" action="/book/AddBookServlet">
    书名：<input type="text" id="add_bookname" name="bookname">
    作者：<input type="text" id="add_author" name="author">
    出版社：<input type="text" id="add_press" name="press">
    书本数量：<input type="number" max="100" min="1" id="add_num" name="num">
    所属类型11：<select id="btypeslt"></select>
    <button type="submit" id="addbookbtn">commit</button>
</form>

<%--$(document).on("click","#addbookbtn",function(){
var form=$("<form id=\"addbook\" action=\"book\/AddBookServlet\"></form>");
var child1=$("<input id=\"add_bookname\" type=\"text\">");
var child2=$("<input id=\"add_author\" type=\"text\">");
var child3=$("<input id=\"add_press\" type=\"text\">");
var child4=$("<input id=\"add_num\" type=\"range\" min=\"1\" max=\"10\">");
var child5=$("<button id=\"addbookbtn\" type='submit'>").text("提交");
    form.append("书名");
    form.append(child1);
    form.append("作者")
    form.append(child2);
    form.append(child3);
    form.append(child4);
    form.append(child5);
    $("#booktable").append(form);
    })--%>
</body>
</html>
