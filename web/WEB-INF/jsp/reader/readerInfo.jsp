<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <div class="clearfix">

        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>欢迎您，${reader.name}    ---个人中心</small>
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/reader/changePhoneNum" method="post">


            <div class="form-group">
                <label> 姓名：</label>
                <input type="text" name="name" class="form-control" value="${reader.name}" disabled>
            </div>
            <div class="form-group">
                <label> 卡号：</label>
                <input type="text" name="readerId" class="form-control" value="${reader.readerId}" disabled>
            </div>
            <div class="form-group">
                <label>性别：</label>
                <input type="text" name="sex" class="form-control" value="${reader.sex}" disabled>
            </div>
            <div class="form-group">
                <label>电话：</label>
                <input type="number" name="phoneNum" class="form-control" value="${reader.phoneNum}" required>
            </div>
            <div class="form-group">
                <label>账号状态：</label>
                <input type="text" name="state" class="form-control" value="${reader.credential.state}" disabled>
            </div>
            <div class="form-group">
                <label>积分：</label>
                <input type="text" name="score" class="form-control" value="${reader.credential.score}" disabled>
            </div>
            <div class="form-group">
                <label>当前借入：</label>
                <input type="text" name="borrowNum" class="form-control" value="${reader.credential.borrowNum}本" disabled>
            </div>

            <div class="form-group">
                <label>书籍名称：</label>
                <input type="submit" class="form-control" value="修改">
            </div>
        </form>
    </div>
</div>

<%--${reader}--%>
</body>
</html>
