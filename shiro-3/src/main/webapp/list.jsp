<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <shiro:user>
        欢迎[<shiro:principal/>]登陆
        <br>
        <a href="logout">登出</a>
        <br>
    </shiro:user>

    <shiro:authenticated>
        用户[<shiro:principal/>]已经身份验证通过
        <br>
    </shiro:authenticated>

    <shiro:notAuthenticated>
        未身份验证(包括记住我)
        <br>
    </shiro:notAuthenticated>

    <!-- 如果登陆的用户具有admin的角色 -->
    <shiro:hasRole name="admin">
        <a href="admin.jsp">Admin Page</a>
        <br>
    </shiro:hasRole>

    <!-- 如果登陆的用户具有user的角色 -->
    <shiro:hasRole name="user">
        <a href="user.jsp">User Page</a>
        <br>
    </shiro:hasRole>

    <a href="testShiroAnnotion">TestShiroAnnotion</a>
    <br>

    <shiro:hasAnyRoles name="admin, user">
        用户[<shiro:principal/>]拥有角色 admin 或者 user
        <br>
    </shiro:hasAnyRoles>

    <!-- 如果当前 Subject 没有 admin 的角色 -->
    <shiro:lacksRole name="admin">
        用户[<shiro:principal/>]没有admin角色
        <br>
    </shiro:lacksRole>

    <shiro:hasPermission name="user:create">
        用户[<shiro:principal/>]拥有权限user:create
        <br>
    </shiro:hasPermission>
</body>
</html>
