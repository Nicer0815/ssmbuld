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
  <title>导航</title>
  <%--教训：重复导包导致二级菜单栏不可见！！！！--%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">--%>
<%--  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>--%>
<%--  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>--%>
  <style type="text/css">
    img{
      width: 100%;
    }
    /*轮播样式*/
    .roll-style{
      margin-top:0;
      height: 600px;
      overflow: hidden;
    }
  </style>

</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<%--轮播--%>
<div id="carousel-example-generic" class="carousel slide roll-style" data-ride="carousel" style="z-index: -1">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="${pageContext.request.contextPath}/static/img/1.jpeg" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/2.jpeg" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/3.jpeg" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/4.jpeg" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    ...
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</body>
</html>
