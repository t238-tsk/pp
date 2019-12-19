<%--
  Created by IntelliJ IDEA.
  User: Admin-Tsk
  Date: 2019/12/6
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/common/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
<div class="text-center" style="width: 70%">
    <h1>添加书籍</h1>
</div>

<f:form  action="${ctx}/book/addBook" method="post" style="width:90%;padding-left: 200px " class="text-center"  modelAttribute="book">
    <table>
        <tr>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 10px" class="text-center"><label class="h4" >书本名称</label></td>
            <td style="width: 400px;padding-top: 10px">
                <f:input path="bookName"></f:input>
                <f:errors path="bookName" cssClass="abc"></f:errors>
            </td>
        </tr>
        <tr>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 10px" class="text-center"><label class="h4" >书本价格</label></td>
            <td style="width: 400px;padding-top: 10px">
                <f:input   path="price"></f:input>
                <f:errors path="price" cssClass="abc"></f:errors>
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 20px" class="text-center"><input type="submit" value="提交"></td>
        </tr>
    </table>

</f:form>
</body>
</html>
