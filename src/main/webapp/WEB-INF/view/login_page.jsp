<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>INDEX</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/login" method="post">
    <p align="center">
    <h1 align="center">Message: ${message}</h1>
        Login: <input type="text" name="usernameForm"/><br/>
        Password: <input type="password" name="passwordForm"/><br/>
        <input type="submit" value="Submit"/>
    </p>
</form>

</body>
</html>