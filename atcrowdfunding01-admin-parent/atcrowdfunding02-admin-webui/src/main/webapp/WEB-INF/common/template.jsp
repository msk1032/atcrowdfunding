<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/2
  Time: 13:29
  后台管理页面模板
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<%@include file="/WEB-INF/common/head.jsp" %>
<body>

<%@include file="/WEB-INF/common/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <%-- 这里显示主题内容  --%>
        </div>

    </div>
</div>
</body>
</html>