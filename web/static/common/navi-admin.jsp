
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-26
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <style type="text/css">
        .my-style-2{
            margin-top: 0;
            margin-bottom: 0;
            -webkit-border-radius: 0;
            -moz-border-radius: 0;
            border-radius: 0;   /*消除圆角*/
        }
    </style>
</head>
<body>
<nav class="navbar my-style-2 navbar-inverse" style="z-index: 8888">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BootStrap图书馆</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/book/allBook">书籍管理</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">工作列表 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/staff/toApprove">借书审批</a></li>
                        <li><a href="${pageContext.request.contextPath}/staff/toReturn">还书审批</a></li>
                        <li><a href="${pageContext.request.contextPath}/staff/toHandle">特殊处理</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/staff/toQuesAns">留言&反馈</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/staff/logout">注销</a></li>
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理员：${staff.name} <span class="caret"></span></a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</body>
</html>