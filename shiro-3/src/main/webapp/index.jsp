<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: luwen
  Date: 2019/1/19
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <shiro:guest>
        欢迎游客登陆,<a href="login.jsp">登陆</a>
    </shiro:guest>
</body>
</html>
