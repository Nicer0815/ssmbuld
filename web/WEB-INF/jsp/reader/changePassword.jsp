<jsp:useBean id="reader" scope="session" type="com.ning.entity.Readers"/>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-29
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码界面</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
密码:${reader.credential.password}

<form action="${pageContext.request.contextPath}/reader/changePassword" method="post">
    <div class="form-group">
        <label>originalPassword</label>
        <input type="password" name="originalPassword" class="form-control" required>
    </div>
    <div class="form-group">
        <label>New Password</label>
        <input type="password" name="newPassword" class="form-control" required>
    </div>
    <%--校验功能待实现--%>
    <div class="form-group">
        <label>New Password Confirm</label>
        <input type="password" name="newPasswordConfirm" class="form-control" required>
    </div>
    <div class="form-group">
        <input type="submit"  class="form-control" value="修改">
    </div>
    <span class="form-control">${msg}</span>
</form>
</body>
</html>
