
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/8/10
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Tool.user"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    user user =(user)session.getAttribute("user"); //从session里把a拿出来，并赋值给user
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
</head>
<body>
用户账号:<%=user.account  %>
<br><br>
姓名：<%=user.name  %>
<br><br>
性别：<%=user.sex  %>
<br><br>
年龄：<%=user.age  %>
<br><br>
电话：<%=user.tel  %>
<br><br>
注册时间：<%=user.retime  %>
<br><br>
所属公司：<%=user.companyname  %>
<br><br>
公司员工编号：<%=user.workid %>
<br><br>
<form action="openmeeting" method="post">
    <input type="submit" value="创办公司会议">
    <form>
<br><br>
<form action="seemeeting" method="post">
    <input type="submit" value="查看公司会议">
<form>
<br><br>
<a href=register_interface.html target="_blank">
    <input type="submit" value="解散公司会议">
</a>
</body>
</html>
