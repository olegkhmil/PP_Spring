<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add user</title>
</head>
<body>
<table style=" width: 100%; border: 4px double black;">
    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="${pageContext.servletContext.contextPath}/admin/all">All users</a>
        </td>
    </tr>
</table>
<form action="${pageContext.servletContext.contextPath}/admin/add" method="post">
    <p align="center">
        Name: <input type="text" name="name"/><br/>
        Age: <input type="number" name="age"/><br/>
        Email: <input type="text" name="email"/><br/>
        Password: <input type="password" name="password"><br/>
        Role: <select name="role_name" id="role_name">
        <option value="USER">user</option>
        <option value="ADMIN">admin</option>
    </select>
        <input type="submit" value="Submit"/>
    </p>
</form>
</body>
</html>