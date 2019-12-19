<%--
  Created by IntelliJ IDEA.
  User: Admin-Tsk
  Date: 2019/12/4
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp"%>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<%--<script>--%>
    <%--function load_submit() {--%>
        <%--location.href = "<%=request.getContextPath()%>/book/bookList";--%>
    <%--}--%>
<%--</script>--%>
<body >
<div  style="width: 80%;height:100px;margin-left: 120px">


</div>

<div  style="width: 80%;height:100px;margin-left: 120px">
    <r:hasRole name="管理员">
    <a href="${ctx}/book/bookList" class="btn btn-success">进入书本查询界面</a>
    </r:hasRole>
    <a href="${ctx}/uploads" class="btn btn-primary">多文件上传</a>

</div>
<div  style="width: 80%;height:60px;margin-left: 100px">
    <r:hasPermission name="system:user:edit">
    <a href="${ctx}/test/bookList2" class="btn btn-success">查询全部</a>
    </r:hasPermission>
    <a href="${ctx}/test/bookGroup" class="btn btn-primary">自定义分组</a>
    <a href="<%=request.getContextPath()%>/face" class="btn btn-primary">面部识别</a>
    <a href="${ctx}/sysUser/loginout" class="btn btn-primary">退出系统</a>

</div>
</body>
</html>


