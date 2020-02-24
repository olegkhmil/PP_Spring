<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>INDEX</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="post">
    <p align="center">
    <h1 align="center">Message: ${message}</h1>
        Email: <input type="text" name="emailIndex"/><br/>
        Password: <input type="password" name="passwordIndex"/><br/>
        <input type="submit" value="Submit"/>
    </p>
</form>

</body>
</html>