<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-30
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帮助和反馈</title>
</head>
<body>
<jsp:include page="/static/common/navi.jsp"></jsp:include>

<style>
    #contact-area {
        padding: 110px 0 120px;
    }

    .contact-box {
        -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 50px 50px 35px;
        background-color: #fff;
        border-radius: 6px;
    }

    .contact-box input {
        height: 50px;
        border: 1px solid #f7f6f5;
        font-size: 15px;
        background-color: #f7f6f5;
        font-weight: 500
    }

    .contact-box textarea {
        border: 1px solid #f7f6f5;
        padding-top: 10px;
        background-color: #f7f6f5;
        font-size: 15px;
        font-weight: 500
    }

    .contact-box button {
        background-color: #325eff;
        border: 0;
        color: #fff;
        padding: 10px 30px;
        border-radius: 4px;
        cursor: pointer;
        outline: 0;
        font-weight: 500;
        font-size: 15px
    }

</style>

<section id="contact-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 offset-lg-2">
                <div class="contact-box text-center">
                    <form id="ajax-contact" action="${pageContext.request.contextPath}/quesAns/askQuestions" method="post">
                        <div class="form-group">
                            <textarea class="form-control" id="question" name="question" placeholder="写下您的意见*"></textarea>
                        </div>
                        <button type="submit">提 交</button>
                        <div id="form-messages"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>



<%--<div class="panel panel-primary">--%>
<%--    <div class="panel-heading">Panel heading without title</div>--%>
<%--    <div class="panel-body">--%>
<%--        Panel content--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="panel panel-info"> <div class="panel-heading">Panel heading without title</div>--%>
<%--    <div class="panel-body">--%>
<%--        Panel content--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="panel panel-info">...</div>--%>
<div class="panel panel-warning"> </div>
<%--<div class="panel panel-danger"> </div>--%>
<%--<div class='main'>　　　--%>
<%--    <div class='waterfall'></div>--%>
<%--</div>--%>

<div class="container" style="margin-top: 20px">
    <div class="row">
        <c:forEach var="quesAns" items="${quesAnsList}">
        <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="panel panel-info">
                    <div class="panel-heading">${quesAns.question}</div>
                    <div class="panel-body">
                            ${quesAns.answer}
                    </div>
                    <div class="panel-footer">
                            ${1900+quesAns.askDate.year}年${1+quesAns.askDate.month}月${1+quesAns.askDate.day}日
                    </div>

                </div>
        </div>
        </c:forEach>
    </div>
</div>
${quesAnsList}

</body>
</html>
