<%@page import="Tool.user"%>
<%@page import="Tool.companylist"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    user user =(user)session.getAttribute("user");
    companylist companylist=(companylist)session.getAttribute("companylist");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>创建公司会议</title>
<body>
    成员id：<%=companylist.list1%>
    <br><br>
    成员姓名：<%=companylist.list2%>
</body>
</html>
