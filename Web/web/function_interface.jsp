
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
    user user =(user)session.getAttribute("user"); //从session里把a拿出来，并赋值给M
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>功能选项</title>
</head>
<body>
<%=user.account  %>
<%=user.pwd  %>
<%=user.name  %>
<%=user.sex  %>
<%=user.age  %>
<%=user.tel  %>
<%=user.retime  %>
<%=user.companyname  %>
<%=user.workid %>
<a href=entercompany_interface.html target="_blank">
    <input type="submit" value="加入公司">
</a>
<br><br>
<a href=register_interface.html target="_blank">
    <input type="submit" value="举行会议">
</a>
<br><br>
</body>
</html>
