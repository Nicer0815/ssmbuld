<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-04-13
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/static/common/navi-admin.jsp"></jsp:include>


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
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                                data-readerid="${record.readerId}"
                                data-bookid="${record.bookId}"
                                data-datee="${record.expectDate}"
                                data-date="${record.borrowDate}">特殊处理</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-1 column"></div>
</div>



<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog glyphicon-align-center modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/staff/handle">
                    <input type="hidden" id="readerId" name="readerId">
                    <input type="hidden" id="bookId" name="bookId">
                    <input type="hidden" id="borrowDate" name="borrowDate">
                    <input type="hidden" id="expectDate" name="expectDate">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">State:</label>
                        <input type="text" class="form-control" list="list-1" name="state" id="recipient-name">
                        <datalist id="list-1">
                            <option value="丢失">丢失</option>
                            <option value="损坏">损坏</option>
                            <option value="逾期">逾期</option>
                        </datalist>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit"
                                class="btn btn-primary">set</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var readerId = button.data('readerid') // Extract info from data-* attributes
        var borrowDate = button.data('date')
        var bookId = button.data('bookid')
        var expectDate = button.data('datee')

        var modal = $(this)
        modal.find('.modal-title').text('readerId: '+readerId+';  ISBN: '+bookId)
        modal.find('.modal-body input[name = readerId]').val(readerId)
        modal.find('.modal-body input[name = bookId]').val(bookId)
        modal.find('.modal-body input[name = borrowDate]').val(borrowDate)
        modal.find('.modal-body input[name = expectDate]').val(expectDate)
    })
</script>
</body>
</html>
