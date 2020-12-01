<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/1
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出错了</title>
</head>
<body>
    <h1>你访问的页面出错了！</h1>
<%--    从请求域中获取异常消息--%>
    <p>${requestScope.exception.message}</p>

</body>
</html>
