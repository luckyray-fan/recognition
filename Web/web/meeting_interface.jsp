<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/8/12
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Tool.user"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    user user =(user)session.getAttribute("user"); //从session里把a拿出来，并赋值给user
%>
<html>
<head>
    <title>会议界面</title>
</head>
<body>
姓名：<%=user.name  %>
&nbsp &nbsp &nbsp
公司：<%=user.companyname  %>
&nbsp &nbsp &nbsp
员工编号：<%=user.workid  %>
&nbsp &nbsp &nbsp
</body>
</html>