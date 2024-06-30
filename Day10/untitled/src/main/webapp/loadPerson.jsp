<%@ page import="com.tentact.pojo.Person" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/6/28
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    Person person = (Person)request.getAttribute("person");

  %>

  <form action="person/updatePerson" method="post">
      id:<input type="text" name="id" value="<%=person.getId()%>"><br/>
      name:<input type="text" name="name" value="<%=person.getName()%>"><br/>
      age:<input type="text" name="age" value="<%=person.getAge()%>"><br/>
      sex:<input type="text" name="sex" value="<%=person.getSex()%>"><br/>

      <input type="submit" value="修改"/>


  </form>
</body>
</html>
