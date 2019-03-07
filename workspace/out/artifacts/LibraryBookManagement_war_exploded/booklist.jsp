<%--
  Created by IntelliJ IDEA.
  User: didi
  Date: 2018/12/23
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.company.bean.BookBean,java.util.ArrayList"  %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    th{
        text-align: left;
    }
    td{
        text-align: left;
    }
</style>
<body>
<%ArrayList<BookBean> booklist= (ArrayList<BookBean>) request.getAttribute("booklist");%>
<table border="1" cellspacing="0">
    <tr>
        <th>bno</th>
        <th>name</th>
        <th>author</th>
        <th>press</th>
        <th>num</th>
        <th>type</th>
        <th>operation</th>
    </tr>
    <%for(BookBean book:booklist){
        %>
    <tr>
        <td><%=book.getBno()%></td>
        <td><%=book.getName()%></td>
        <td><%=book.getAuthor()%></td>
        <td><%=book.getPress()%></td>
        <td> <%=book.getNum()%></td>
        <td><%=book.getType()%></td>
        <%if (book.getStatus()==0||book.getNum()<1){%>
        <td>cannot borrow</td>
        <%}else{%>
        <td>
            <form action=""></form>
            <button onclick="borrowbook()">borrow</button>
        </td>
        <%}%>
    </tr>

    <%}
    %>
</table>

</body>
<script>
    function borrowbook(){

    }
</script>
</html>
