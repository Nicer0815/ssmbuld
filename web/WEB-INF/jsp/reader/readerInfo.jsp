<jsp:useBean id="reader" scope="session" type="com.ning.entity.Readers"/>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-29
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读者信息页面</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
${reader.name}
${reader.phoneNum}
${reader.readerId}
${reader.sex}
</body>
</html>
