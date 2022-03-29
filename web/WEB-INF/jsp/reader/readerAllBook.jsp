<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-26
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读者-书籍</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/static/img/gm.jpg" height="240" width="320" alt="...">
                    <div class="caption">
                        <h3>《鬼灭之刃》</h3>
                        <p>悬疑历史</p>
                        <p><a href="#" class="btn btn-primary" role="button">收藏</a>
                            <a href="#" class="btn btn-default" role="button">借阅</a>
                        </p>
                    </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/static/img/gm.jpg" height="240" width="320" alt="...">
                <div class="caption">
                    <h3>《鬼灭之刃》</h3>
                    <p>悬疑历史</p>
                    <p><a href="#" class="btn btn-primary" role="button">收藏</a>
                        <a href="#" class="btn btn-default" role="button">借阅</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/static/img/gm.jpg" height="240" width="320" alt="...">
                <div class="caption">
                    <h3>《鬼灭之刃》</h3>
                    <p>悬疑历史</p>
                    <p><a href="#" class="btn btn-primary" role="button">收藏</a>
                        <a href="#" class="btn btn-default" role="button">借阅</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/static/img/gm.jpg" height="240" width="320" alt="...">
                <div class="caption">
                    <h3>《鬼灭之刃》</h3>
                    <p>悬疑历史</p>
                    <p><a href="#" class="btn btn-primary" role="button">收藏</a>
                        <a href="#" class="btn btn-default" role="button">借阅</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
