<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-29
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅记录</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>
records:${records}
<div class="row clearfix">
    <div class="col-md-1 column"></div>
    <div class="col-md-10 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>ReaderID</th>
                <th>ISBN</th>
                <th>借出时间</th>
                <th>状态</th>
                <th>最迟归还</th>
                <th>归还日期</th>
            </tr>
            </thead>
            <%--书籍从数据库中查询出来，从这个list中遍历出来：foreach--%>
            <tbody>
            <%--<jsp:useBean id="records" scope="session" type="java.util.List"/>--%>
            <c:forEach var="record" items="${records}">
                <tr>
                    <td>${record.readerId}</td>
                    <td>${record.bookId}</td>
                    <td>${1900+record.borrowDate.year}年${record.borrowDate.month}月${record.borrowDate.day}日</td>
                    <td>${record.state}</td>
                    <td>${1900+record.expectDate.year}年${record.expectDate.month}月${record.expectDate.day}日</td>
                    <td>${1900+record.returnDate.year}年${record.returnDate.month}月${record.returnDate.day}日</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-1 column"></div>
</div>

</body>
</html>
