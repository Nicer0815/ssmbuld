<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-23
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <script>
        $(function () {
            $('#header').load('header.html');
        })
    </script>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
${msg}

</body>
</html>
