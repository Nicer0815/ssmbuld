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
      width: auto;
      height: auto;
    }
    /*轮播样式*/
    .roll-style{
      margin-top:0;
      height: 60%;
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
      <img src="${pageContext.request.contextPath}/static/img/6.png"  alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/5.png" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/8.png" alt="...">
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/static/img/9.png" alt="...">
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
<h1 class="text-center" style="margin-top: 50px">站点推荐</h1>
<div class="container" style="margin-top: 30px">
  <div class="row">
    <div class="col-lg-3 col-md-4 col-sm-6">
      <div class="thumbnail" href="#">
        <a href="${pageContext.request.contextPath}/reader/toLogin">
          <img src="${pageContext.request.contextPath}/static/img/user.png"
               style="width:240px;height:240px;" alt="Sorry,图片找不到了...."></a>
        <div class="caption">
          <h4 class="text-center">用户入口</h4>
          <p class="text-center">以用户身份进入本站</p>
          <p class="text-center">点击进入看看吧</p>
        </div>
      </div>
    </div>
      <div class="col-lg-3 col-md-4 col-sm-6">
      <div class="thumbnail" href="#">
        <a href="${pageContext.request.contextPath}/staff/toLogin">
          <img src="${pageContext.request.contextPath}/static/img/staff.png"
               style="width:240px;height:240px;" alt="Sorry,图片找不到了...."></a>
        <div class="caption">
          <h4 class="text-center">管理员入口</h4>
          <p class="text-center">以管理员身份进入本站</p>
          <p class="text-center">管理员请在此进入</p>
        </div>
      </div>
      </div>
    <div class="col-lg-3 col-md-4 col-sm-6">
      <div class="thumbnail" href="#">
        <a href="${pageContext.request.contextPath}/reader/allBook">
          <img src="${pageContext.request.contextPath}/static/img/books.png"
               style="width:240px;height:240px;" alt="Sorry,图片找不到了...."></a>
        <div class="caption">
          <h4 class="text-center">图书大厅</h4>
          <p class="text-center">点击进入图书大厅</p>
          <p class="text-center">有超级多书哦</p>
        </div>
      </div>
    </div>
      <div class="col-lg-3 col-md-4 col-sm-6">
      <div class="thumbnail" href="#">
        <a href="${pageContext.request.contextPath}/reader/aboutUs">
          <img src="${pageContext.request.contextPath}/static/img/gm.jpg"
               style="width:240px;height:240px;" alt="Sorry,图片找不到了...."></a>
        <div class="caption">
          <h4 class="text-center">关于我们</h4>
          <p class="text-center">吉林大学软件学院2019级</p>
          <p class="text-center">没什么故事哈哈哈</p>
        </div>
      </div>
      </div>
    </div>
  </div>
</body>
</body>
</html>
