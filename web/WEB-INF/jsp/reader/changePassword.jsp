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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#password').focus(function(){
                // 密码框获得焦点，追加样式.password
                $('#owl').addClass('password');
            }).blur(function(){
                // 密码框失去焦点，移除样式.password
                $('#owl').removeClass('password');
            })
            $('#password1').focus(function(){
                // 密码框获得焦点，追加样式.password
                $('#owl').addClass('password');
            }).blur(function(){
                // 密码框失去焦点，移除样式.password
                $('#owl').removeClass('password');
            })
            $('#password2').focus(function(){
                // 密码框获得焦点，追加样式.password
                $('#owl').addClass('password');
            }).blur(function(){
                // 密码框失去焦点，移除样式.password
                $('#owl').removeClass('password');
            })
        })
    </script>
</head>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<body style="height: 100vh;
    background: linear-gradient(200deg,#72afd3,#96fbc4);">
<%--密码:${reader.credential.password}--%>
<div class="container center-block" style="align-items: center;margin-top: 200px;margin-left: 800px">
    <div class="login-box">
        <div class="owl" id="owl">
            <div class="hand"></div>
            <div class="hand hand-r"></div>
            <div class="arms">
                <div class="arm"></div>
                <div class="arm arm-r"></div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/reader/changePassword" method="post">
            <div class="input-box">
                <input type="password" id="password" name="originalPassword" placeholder="原密码" required>
                <input type="password" name="newPassword" placeholder="新密码" id="password1" required>
                <input type="password" name="newPasswordConfirm" placeholder="确认密码" id="password2" required>
                <button type="submit">修改</button>
            </div>
        </form>
    </div>
    <span>${msg}</span>
</div>
</body>
</html>
