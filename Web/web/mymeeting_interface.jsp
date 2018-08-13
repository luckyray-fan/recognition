<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/8/13
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Tool.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    user user =(user)session.getAttribute("user");
    meetinglist meetinglist=(meetinglist)session.getAttribute("meetinglist");
    String []list1=meetinglist.list1.split("，");
    String []list2=meetinglist.list2.split("，");
    String []list3=meetinglist.list3.split("，");
%>
<html>
<head>
    <title>公司名-<%=user.name  %></title>
</head>
<body>
<% for(int i = 0;i<list1.length;i++) {%>
    <%=list1[i] %>
    &nbsp &nbsp &nbsp
    <%=list2[i] %>
    &nbsp &nbsp &nbsp
    <%=list3[i] %>
<br><br>
<br><br>
<% }
%>





</body>
</html>
