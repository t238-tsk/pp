<%--
  Created by IntelliJ IDEA.
  User: Admin-Tsk
  Date: 2019/12/7
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" uri="http://www.springframework.org/tags" %>

<%@include file="/common/head.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div style="width:80%;height: 70px;margin-left: 100px">
        <h1><t:message code="title"/></h1>
</div>
<f:form action="${ctx}/book/bookList" method="post" class="form-control" style="width: 80%;height:60px;margin-left: 100px" modelAttribute="book">
    <t:message code="book.name"></t:message> :<f:input path="bookName"></f:input>
    <input type="submit" class="btn btn-success" value="<t:message code="list.lable"></t:message> ">
    <a href="${ctx}/book/toAdd" class="btn btn-primary"><t:message code="add.lable"></t:message> </a>
</f:form>
<table class="table" style="width: 80%;margin-left: 100px">
    <caption class="text-center">基本的表格布局</caption>
    <thead>
    <tr>
        <th>编号</th>
        <th>名字</th>
        <th>价格</th>
        <th>图片</th>
        <th>编辑</th>
    </tr>
    </thead>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.bookId}</td>
            <td>${b.bookName}</td>
            <td>${b.price}</td>
            <td><img src="${ctx}/myFile/download?fileId=${b.img}" style="width: 100px;height: 100px"></td>
            <td>
                <a href="${ctx}/myFile/toUpload?bookId=${b.bookId}" class="btn btn-primary"><t:message code="book.img"></t:message> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<div  style="width: 80%;height:100px;margin-left: 120px">
    <a href="${ctx}/change?locale=zh" class="btn btn-success">中文</a>
    <a href="${ctx}/change?locale=en" class="btn btn-primary">English</a>
     <%--<a href="${ctx}/uploads" class="btn btn-primary">多文件上传</a>--%>

    <z:test pageBean="${pageBean}"></z:test>
</div>
<%--<div  style="width: 80%;height:60px;margin-left: 100px">--%>
    <%--<a href="${ctx}/test/bookList2" class="btn btn-success">查询全部</a>--%>
    <%--<a href="${ctx}/test/bookGroup" class="btn btn-primary">自定义分组</a>--%>
    <%--<a href="<%=request.getContextPath()%>/face" class="btn btn-primary">面部识别</a>--%>
<%--</div>--%>


</body>
</html>
