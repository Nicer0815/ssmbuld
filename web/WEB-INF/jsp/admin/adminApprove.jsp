<jsp:useBean id="staff" scope="session" type="com.ning.entity.Staff"/>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-05-14
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="/static/common/navi-admin.jsp"></jsp:include>
<script type="text/javascript">
    function del(){
        return confirm("确认借出？");
    }
</script>

<body>
<div class="container">
    <div class="clearfix">

        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${staff.name}    ---借书审批</small>
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/staff/approveBorrow" method="post">


            <div class="form-group">
                <label> ISBN：</label>
                <input type="text" name="bookId" class="form-control"  required>
            </div>
            <div class="form-group">
                <label> readerId：</label>
                <input type="text" name="readerId" class="form-control"  required>
            </div>


            <div class="form-group">
                <label></label>
                <input type="submit" class="form-control" value="借 出" onclick="del()">
            </div>
        </form>
    </div>
</div>


</body>
</html>
