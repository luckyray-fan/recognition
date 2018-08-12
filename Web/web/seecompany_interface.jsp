<%@page import="Tool.user"%>
<%@page import="Tool.companylist"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    user user =(user)session.getAttribute("user");
    companylist companylist=(companylist)session.getAttribute("companylist");
    String []list1=companylist.list1.split("，");
    String []list2=companylist.list2.split("，");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>创建公司会议</title>
<body>
选择会议成员
<br><br>
<% for(int i = 0;i<list1.length;i++) {%>
<form action="holdmeeting" method="post">
    <input type="text" name="workid" value=<%=list1[i] %>>
    &nbsp &nbsp &nbsp
    <input type="text" name="name" value=<%=list2[i] %>>
    &nbsp &nbsp &nbsp
    <input type="submit" value="+">
</form>
    <br><br>
    <br><br>
    <% }
%>
<br><br>
<form action="deletemeeting" method="post">
    <input type="submit" value="退出创建会议">
</form>
<br><br>
<br><br>
<a href="function_interface.jsp">
    <input type="submit" value="完成创建会议">
</a>
</body>
</html>
