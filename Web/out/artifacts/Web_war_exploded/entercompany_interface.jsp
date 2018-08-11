<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/8/10
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Tool.user"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
我们发现您还没有加入任何公司，是否考虑现在加入您所在的公司
<br>
选择您的公司：
<select name="company ">
    <option>中国石油</option>
</select><br>

请输入您公司的对应的邀请码<input type="text" name="identifyingcode"><br>

请输入您的员工号：<input type="text" name="workid"><br>

<input type="submit" value="申请加入"><br>
</body>
</html>
