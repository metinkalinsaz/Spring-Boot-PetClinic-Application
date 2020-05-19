
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Title Here</title>
</head>
<body>
<%--@elvariable id="owner" type=""--%>
<form:form modelAttribute="owner" method="post">
    FirstName:<form:input path="firstName"/><br/>
    LastName:<form:input path="lastName"/><br/>
    <form:button name="submit">Delete</form:button>
</form:form>

</body>
</html>
