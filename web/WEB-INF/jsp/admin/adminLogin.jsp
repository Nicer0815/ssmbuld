<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-30
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>登录页面</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>

<div class="clearfix">

  <div class="col-md-12 column">
    <div class="page-header">
      <h1>
        <small>破烂的管理员登录页面</small>
      </h1>
    </div>
  </div>
  <form action="${pageContext.request.contextPath}/staff/login" method="post">
    <div class="form-group">
      <label>jobId:</label>
      <input type="text" name="jobId" class="form-control" required>
    </div>
    <div class="form-group">
      <label>password:</label>
      <input type="password" name="password" class="form-control" required>
    </div>
    <div class="form-group">
      <input type="submit"  class="form-control" value="登录">
    </div>
  </form>
</div>
${msg}

</body>
</html>