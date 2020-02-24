<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.01.2020
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AllUsers</title>
</head>
<body>
<table style=" width: 100%; border: 4px double black;">
    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="${pageContext.servletContext.contextPath}/add">Add new user</a>
<%--            <a style="position: absolute; right: 25px"  href="${pageContext.servletContext.contextPath}/logout">Logout</a>--%>
        </td>

    </tr>
</table>

<p align="center">All Users</p>
<table align="center" border="1px">
    <tr>
        <th>User Id</th>
        <th>User Name</th>
        <th>User Age</th>
        <th>User email</th>
        <th>password</th>
        <th>role</th>
        <th colspan="2">Action</th>
    </tr>

    <c:forEach var="user" items="${usersFromDB}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                    <label>
                        <input type="text" hidden name="id" value="${user.id}"/>
                    </label>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                    <label>
                        <input type="number" hidden name="id" value="${user.id}"/>
                    </label>
                    <input type="submit" value="Update"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
