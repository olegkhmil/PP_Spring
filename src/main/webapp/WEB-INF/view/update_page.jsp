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
            <a href="${pageContext.servletContext.contextPath}/admin/all">All users</a>
        </td>
    </tr>
</table>
<form action="${pageContext.servletContext.contextPath}/admin/update" method="post">
    <input type="hidden" name="id" value="${userFromDB.id}">
    <input type="hidden" name="oldPass" value="${userFromDB.hash_password}">

    <p align="center">
        Name: <input type="text" name="name" value="${userFromDB.name}"/><br/>
        Age: <input type="number" name="age" value="${userFromDB.age}"/><br/>
        Email: <input type="text" name="email" value="${userFromDB.email}"/><br/>
        Password: <input type="password" name="newPass" value=""><br/>
        <%--        State: <input type="text" name="state" value="${userFromDB.state}"><br/>--%>
        State: <select name="state" id="state">
        <option value="${userFromDB.state}">"${userFromDB.state}"</option>
        <option value="DELETED">DELETED</option>
        <option value="ACTIVE">ACTIVE</option>
        <option value="BANNED">BANNED</option>
    </select><br/>
        Role:
        <c:forEach var="roleDB" items="${rolesFromDB}">
            <input type="checkbox" name="option1" value="${roleDB.role_name}"
            <c:forEach var="userRole" items="${userFromDB.roles}">
            <c:if test="${roleDB.role_name.equals(userRole.role_name)}">
                   checked
            </c:if>
            </c:forEach>
            >${roleDB.role_name}<Br>
        </c:forEach>
        <input type="submit" value="Submit"/>
    </p>
</form>
</body>
</html>