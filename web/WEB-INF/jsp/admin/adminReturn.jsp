<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-04-13
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/static/common/navi-admin.jsp"></jsp:include>
<script type="text/javascript">
    function del(){
        return confirm("确认还书？");
    }
</script>


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
                <th>状态</th>
            </tr>
            </thead>
            <%--书籍从数据库中查询出来，从这个list中遍历出来：foreach--%>
            <tbody>

            <c:forEach var="record" items="${unReturnRecords}">
                <tr>
                    <td>${record.readerId}</td>
                    <td>${record.bookId}</td>
                    <td>${record.title}</td>
                    <td>${record.author}</td>
                    <td>${1900+record.borrowDate.year}年${1+record.borrowDate.month}月${1+record.borrowDate.day}日</td>
                    <td>${record.state}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/staff/return?readerId=${record.readerId}&borrowDate=${record.borrowDate}&bookID=${record.bookId}" onclick="return del();"
                                class="btn btn-danger">归还</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-1 column"></div>
</div>


<%--${unReturnRecords}--%>
</body>
</html>
