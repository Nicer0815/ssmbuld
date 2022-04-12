<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-23
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>登录页面</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $('#password').focus(function(){
        // 密码框获得焦点，追加样式.password
        $('#owl').addClass('password');
      }).blur(function(){
        // 密码框失去焦点，移除样式.password
        $('#owl').removeClass('password');
      })
    })
  </script>
</head>
<body style="height: 100vh;
    /* 弹性布局 居中 */
    display: flex;
    justify-content: center;
    align-items: center;
    /* 渐变背景 */
    background: linear-gradient(200deg,#72afd3,#96fbc4);">

<div class="login-box">
  <div class="owl" id="owl">
    <div class="hand"></div>
    <div class="hand hand-r"></div>
    <div class="arms">
      <div class="arm"></div>
      <div class="arm arm-r"></div>
    </div>
  </div>
  <form action="${pageContext.request.contextPath}/reader/login" method="post">
    <div class="input-box">
      <input type="text" placeholder="Reader ID" name="readerId" required>
      <input type="password" placeholder="Password" id="password" name="password" required>
      <button type="submit">登录</button>
    </div>
  </form>
</div>
${msg}

</body>
</html>