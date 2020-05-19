<%--
  Created by IntelliJ IDEA.
  User: EXT02D18579
  Date: 19.05.2020
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
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
    <form:button name="submit">Create</form:button>
</form:form>

</body>
</html>
