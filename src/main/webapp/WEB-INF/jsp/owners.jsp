<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inert Title Here</title>
</head>
<body>
<table>

    <thead>
    <tr style="font-weight: bold" bgcolor="#e0ffffblue">
        <td>ID</td>
        <td>FIRSTNAME</td>
        <td>LASTNAME</td>
    </tr>
    </thead>
    <c:forEach items="${owners}" var="owner" varStatus="status">
        <tr bgcolor="${status.index % 2==0 ? 'white' : '#e0ffff'}">
            <td>${owner.id}</td>
            <td>${owner.firstName}</td>
            <td>${owner.lastName}</td>

        </tr>

    </c:forEach>

</table>

</body>
</html>
