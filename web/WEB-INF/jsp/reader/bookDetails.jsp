<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-04-04
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<div class="container" style="margin-top: 30px">
    <div class="row">
        <div class="col-lg-6">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/ImageLoading?bookId=${detailBook.bookId}"
                     style="width:800px;height:800px;" alt="Sorry,图片找不到了....">
            </div>
        </div>
        <div class="col-lg-6">
            <div class="caption">
                <h1>《${detailBook.title}》</h1>
                <h4>类   别：${detailBook.subject}</h4>
                <h4>ISBN  ：${detailBook.bookId}</h4>
                <h4>出版商 ：${detailBook.press}</h4>
                <h4>作   者：${detailBook.author}</h4>
                <h4>状   态：${detailBook.state}</h4>
                <h4>发行日期：${detailBook.pubDate}</h4>
                <h4>原   价：${detailBook.price}元</h4>
                <h4>库   存：${detailBook.amount}</h4>
                <h4>馆藏位置：${bookState.location}</h4>
                <h4>剩余可借：${bookState.remainNum}</h4>

                    <a href="${pageContext.request.contextPath}/collect/doCollect?bookId=${detailBook.bookId}" class="btn btn-primary" role="button">收藏</a>
                    <a href="#" class="btn btn-default" role="button">借阅</a>
                    <form action="${pageContext.request.contextPath}/comment" method="post">
                        <input type="hidden" name="bookId" value="${detailBook.bookId}">
                        <div class="col-lg-10">
                            <input type="text" name="content" class="form-control" required style="margin-top: 20px" placeholder="写下您的评论">
                        </div>
                        <div class="col-lg-2">
                            <input type="submit" class="form-control" value="评论" style="margin-top: 20px">
                        </div>
                    </form>


                <div style="margin-top: 20px">

                        <br><br><br>
                        <h2>评论区：</h2>
                        <c:forEach var="comment" items="${comments}">
                            <p>
                                from：${comment.readerId}的评价：<br>
                                ${comment.content}
                            </p>
                        </c:forEach>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
