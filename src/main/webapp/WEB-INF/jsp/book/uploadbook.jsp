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


</head>
<body>
<div class="text-center" style="width: 70%">
    <h1>图片上传</h1>
</div>

<form  action="${ctx}/myFile/upload" method="post" style="width:90%;padding-left: 200px " class="text-center" enctype="multipart/form-data" >
    <table>
        <tr>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 10px" class="text-center"><label class="h4" >书本编号</label></td>
            <td style="width: 400px;padding-top: 10px">
                <input  type="text" name="bookId" value="${param.bookId}"  />
            </td>
        </tr>
        <tr>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 10px" class="text-center"><label class="h4" >书本图片</label></td>
            <td style="width: 400px;padding-top: 10px">
                <input type="file" name="file"   />
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="width:180px;padding-left: 20px;padding-right: 20px;padding-top: 20px" class="text-center"><input type="submit" value="提交"></td>
        </tr>
    </table>

</form>



</body>
</html>

