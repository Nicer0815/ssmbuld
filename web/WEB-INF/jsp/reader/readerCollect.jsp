<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-30
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收藏夹</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<%--${bookCollectList}--%>


<div class="row clearfix">
    <div class="col-md-1 column"></div>
    <div class="col-md-10 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>ISBN</th>
                <th>书籍</th>
                <th>作者</th>
                <th>收藏日期</th>
            </tr>
            </thead>
            <%--书籍从数据库中查询出来，从这个list中遍历出来：foreach--%>
            <tbody>

            <c:forEach var="collection" items="${bookCollectList}">
                <tr>
                    <td>${collection.bookId}</td>
                    <td>${collection.title}</td>
                    <td>${collection.author}</td>
                    <td>${1900+collection.collectDate.year}年${collection.collectDate.month+1}月${collection.collectDate.date}日</td>

                    <td>
                        <a href="${pageContext.request.contextPath}/book/details?bookId=${collection.bookId}">查看详情</a>
                        &nbsp;| &nbsp;
                        <a href="${pageContext.request.contextPath}/collect/dropCollect?bookId=${collection.bookId}">取消收藏</a>
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
