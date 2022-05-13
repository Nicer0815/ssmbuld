<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-04-13
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言&反馈</title>
</head>
<body>
<jsp:include page="/static/common/navi-admin.jsp"></jsp:include>

<div class="row clearfix">
    <div class="col-md-2 column"></div>
    <div class="col-md-8 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>读者ID</th>
                <th>日期</th>
                <th>留言</th>
                <th>回复</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="quesAns" items="${unSolvedQuesAns}">
                <tr>
                    <td>${quesAns.readerId}</td>
                    <td>${quesAns.askDate}</td>
                    <td>${quesAns.question}</td>
                    <td>${quesAns.answer}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#exampleModal"
                                data-whatever="${quesAns.readerId}"
                                data-answer="${quesAns.answer}"
                                data-date="${quesAns.askDate}"
                                data-question="${quesAns.question}">答 复</button>
                        &nbsp;|&nbsp;
                        <a href="${pageContext.request.contextPath}/staff/deleteQuesAns?readerId=${quesAns.readerId}&askDate=${quesAns.askDate}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach var="quesAns" items="${solvedQuesAns}">
                <tr>
                    <td>${quesAns.readerId}</td>
                    <td>${quesAns.askDate}</td>
                    <td>${quesAns.question}</td>
                    <td>${quesAns.answer}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                                data-whatever="${quesAns.readerId}"
                                data-answer="${quesAns.answer}"
                                data-date="${quesAns.askDate}"
                                data-question="${quesAns.question}">修 改</button>
                        &nbsp;|&nbsp;
                        <a href="${pageContext.request.contextPath}/staff/deleteQuesAns?readerId=${quesAns.readerId}&askDate=${quesAns.askDate}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-2 column"></div>
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
                <form action="${pageContext.request.contextPath}/staff/quesAnsReply">
                    <input type="hidden" id="readerId" name="readerId">
                    <input type="hidden" id="askDate" name="askDate">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Message:</label>
                        <input type="text" class="form-control" name="question" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Reply:</label>
                        <textarea class="form-control" name="answer" id="message-text"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit"
                                class="btn btn-primary">Send message</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        var askDate = button.data('date')
        //alert(askDate)
        var answer = button.data('answer')
        var questions = button.data('question')
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input[name = readerId]').val(recipient)
        modal.find('.modal-body input[name = askDate]').val(askDate)
        modal.find('.modal-body input[name = question]').val(questions)
        modal.find('.modal-body textarea').val(answer)
    })
</script>
</body>
</html>
