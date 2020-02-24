<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update user</title>
</head>
<body>
<table style=" width: 100%; border: 4px double black;">
    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="${pageContext.servletContext.contextPath}/allUsers">All users</a>
        </td>
    </tr>
</table>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <input type="hidden" name="id" value="${userFromDB.id}">
    <p align="center">
        Name: <input type="text" name="name" value="${userFromDB.name}"/><br/>
        Age: <input type="number" name="age" value="${userFromDB.age}"/><br/>
        Email: <input type="text" name="email" value="${userFromDB.email}"/><br/>
        Password: <input type="password" name="password" value="${userFromDB.password}"><br/>
        Role: <select name="role" id="role">
        <option value="${userFromDB.role}">${userFromDB.role}</option>
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select>
        <input type="submit" value="Submit"/>
    </p>
</form>
</body>
</html>