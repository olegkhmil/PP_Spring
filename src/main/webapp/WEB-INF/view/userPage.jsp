<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user page</title>
</head>
<body>

<a style="position: absolute; right: 25px" href="${pageContext.servletContext.contextPath}/logout">Logout</a>

    <p>
        <h1>Hello ${user.name}</h1>
    </p>
</body>
</html>