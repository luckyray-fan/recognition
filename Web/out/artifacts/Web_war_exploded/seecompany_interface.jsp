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
<% for(int i = 0;i<list1.length;i++) {%>
成员id:<%=list1[i]%>
成员姓名:<%=list2[i]%>
<br><br>
<% }
%>
</body>
</html>
