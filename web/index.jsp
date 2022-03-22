<%--
  Created by IntelliJ IDEA.
  User: Nicer
  Date: 2022/3/15
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
      a{
       text-decoration: none;
        color: black;
        font-size: 18px;
      }
      h3{
          width: 180px;
          height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: deepskyblue;
        border-radius: 5px;
      }
    </style>


  </head>
  <body>
    <h3>
      <a href="${pageContext.request.contextPath}/book/allBook">进入书籍页面</a>
    </h3>
  </body>
</html>
