<%--
  Created by IntelliJ IDEA.
  User: Nicer
  Date: 2022/3/16
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/static/common/navi-admin.jsp"></jsp:include>
    <div class="container">
        <div class="clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>新增书籍</small>
                    </h1>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/book/addBook" method="post">
                   <div class="form-group">
                       <label>书籍ID：</label>
                       <input type="text" name="bookId" class="form-control" required>
                   </div>
                   <div class="form-group">
                       <label> 书籍名称：</label>
                       <input type="text" name="title" class="form-control" required>
                   </div>
                <div class="form-group">
                    <label> 作者：</label>
                    <input type="text" name="author" class="form-control" required>
                </div>
                   <div class="form-group">
                       <label>发行日期：</label>
                       <input type="date" name="pubDate" class="form-control" >
                   </div>
                   <div class="form-group">
                       <label>发行商：</label>
                       <input type="text" name="press" class="form-control" required>
                   </div>
                    <div class="form-group">
                        <label>总数量：</label>
                        <input type="text" name="amount" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>类别：</label>
                        <input type="text" name="subject" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>价格：</label>
                        <input type="number" name="price" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>状态：</label>
                        <input type="text" name="state" class="form-control" required>
                    </div>
                   <div class="form-group">
                       <input type="submit" class="form-control" value="添加">
                   </div>
            </form>
        </div>
    </div>

</body>
</html>
