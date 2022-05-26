<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-30
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>续借办理</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
<%--${records}--%>

<div class="row clearfix">
  <div class="col-md-1 column"></div>
  <div class="col-md-10 column">
    <table class="table table-hover table-striped">
      <thead>
      <tr>
        <th>读者账号</th>
        <th>ISBN</th>
        <th>书籍</th>
        <th>作者</th>
        <th>借出时间</th>
        <th>归还期限</th>
        <th>状态</th>
      </tr>
      </thead>
      <%--书籍从数据库中查询出来，从这个list中遍历出来：foreach--%>
      <tbody>

      <c:forEach var="record" items="${records}">
        <tr>
          <td>${record.readerId}</td>
          <td>${record.bookId}</td>
          <td>${record.title}</td>
          <td>${record.author}</td>
          <td>${1900+record.borrowDate.year}年${record.borrowDate.month}月${record.borrowDate.day}日</td>
          <td>${1900+record.expectDate.year}年${record.expectDate.month}月${record.expectDate.day}日</td>
          <td>${record.state}</td>
          <td>
            <a href="${pageContext.request.contextPath}/record/return?bookId=${record.bookId}&borrowDate=${record.borrowDate}">归还</a>
            &nbsp;| &nbsp;
            <a href="${pageContext.request.contextPath}/record/continue?bookId=${record.bookId}&borrowDate=${record.borrowDate}">续借</a>
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
