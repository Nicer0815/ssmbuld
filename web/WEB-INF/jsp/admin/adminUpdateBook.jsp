<%--
  Created by IntelliJ IDEA.
  User: Nicer
  Date: 2022/3/17
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍信息</title>
<%--    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<div class="container">
    <div class="clearfix">

        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
            <%--前端传递隐藏域--%>
                <input type="hidden" name="bookId" value="${books.bookId}">

                <div class="form-group">
                    <label> 书籍名称：</label>
                    <input type="text" name="title" class="form-control" value="${books.title}" required>
                </div>
                <div class="form-group">
                    <label> 作者：</label>
                    <input type="text" name="author" class="form-control" value="${books.author}" required>
                </div>
                <div class="form-group">
                    <label>发行日期：</label>
                    <input type="date" name="pubDate" class="form-control" value="${books.pubDate}" required>
                </div>
                <div class="form-group">
                    <label>发行商：</label>
                    <input type="text" name="press" class="form-control" value="${books.press}" required>
                </div>
                <div class="form-group">
                    <label>总数量：</label>
                    <input type="text" name="amount" class="form-control" value="${books.amount}" required>
                </div>
                <div class="form-group">
                    <label>类别：</label>
                    <input type="text" name="subject" class="form-control" value="${books.subject}" required>
                </div>
                <div class="form-group">
                    <label>价格：</label>
                    <input type="number" name="price" class="form-control" value="${books.price}" required>
                </div>
                <div class="form-group">
                    <label>状态：</label>
                    <input type="text" name="state" class="form-control" value="${books.state}" required>
                </div>


            <div class="form-group">
                <label>书籍名称：</label>
                <input type="submit" class="form-control" value="修改">
            </div>
        </form>
    </div>
</div>

<body>

</body>
</html>
