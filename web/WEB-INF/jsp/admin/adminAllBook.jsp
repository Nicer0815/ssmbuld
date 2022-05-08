<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nicer
  Date: 2022/3/15
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
</head>
<body>
    <jsp:include page="/static/common/navi-admin.jsp"></jsp:include>
    <div class="container">
        <div class="clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>书籍列表 ----显示所有书籍</small>
                    </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 column">
                    <%--toAddBook--%>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                </div>
                <div class="col-md-8 column">
                    <%--查询书籍--%>
                    <form class="form-inline" action="${pageContext.request.contextPath}/book/queryBook" method="post" style="float: right">
                        <input type="text" name="bookName" class="form-control" placeholder="请输入要查询的书籍名称">
                        <input type="submit" value="查询" class="btn btn-primary">
                    </form>
                </div>
            </div>

        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-1 column"></div>
        <div class="col-md-10 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>作者</th>
                        <th>发行日期</th>
                        <th>出版商</th>
                        <th>数量</th>
                        <th>书籍类别</th>
                        <th>书籍单价</th>
                        <th>书籍状态</th>
                    </tr>
                </thead>
                <%--书籍从数据库中查询出来，从这个list中遍历出来：foreach--%>
                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookId}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${1900+book.pubDate.year}年${book.pubDate.month}月${book.pubDate.day}日</td>
                            <td>${book.press}</td>
                            <td>${book.amount}</td>
                            <td>${book.subject}</td>
                            <td>${book.price}</td>
                            <td>${book.state}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.bookId}">修改</a>
                                &nbsp;| &nbsp;
                                <a href="${pageContext.request.contextPath}/book/deleteBook?id=${book.bookId}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-1 column"></div>
    </div>



</body>
</html>
