<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/2
  Time: 13:29
  后台管理页面模板
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<%@include file="/WEB-INF/head.jsp" %>
<head>
    <link rel="stylesheet" url="css/pagination.css">
    <script type="text/javascript" src="jquery/jquery.pagination.js"></script>

    <script type="text/javascript">
        $(function () {
            initPagination();
        });

        function initPagination() {
            var totalRecord = ${requestScope.pageInfo.total};

            var properties = {
                //边缘页数
                num_edge_entries: 3,
                //主体页数
                num_display_entries: 5,
                //点击翻页按钮之后的回调函数
                callback:pageSelectCallback,
                //当前页
                current_page: ${requestScope.pageInfo.pageNum-1},
                prev_text: "上一页",
                next_text: "下一页",
                items_per_page: ${requestScope.pageInfo.pageSize},
            }
            //调用分页导航条对应的jQuery对象的 pagination生成导航条
            $("#Pagination").pagination(totalRecord, properties);
        }
        // 翻页过程中执行的回调函数
        // 点击“上一页”、“下一页”或“数字页码”都会触发翻页动作，从而导致当前函数被调用
        //pageIndex是当前页码的索引 pageIndex + 1 = pageNum
        function pageSelectCallback(pageIndex, jQuery) {
            var pageNum = pageIndex+1;
            //页面跳转
            window.location.href = "admin/page.html?pageNum="+pageNum;
            //取消当前超链接的默认行为
            return false


        }
    </script>
</head>
<body>

<%@include file="/WEB-INF/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <%-- 这里显示主题内容  --%>
            <form action="admin/page.html" method="post" class="form-inline" role="form" style="float: left;">
                <div class="form-grouphas-feedback">
                    <div class="input-group">
                        <div class="input-group-addon">查询条件
                        </div>
                        <input name="keyword"class="form-controlhas-success" type="text" placeholder="请输入查询条件">
                    </div>
                </div>
                <button type="submit"class="btnbtn-warning">
                    <i class="glyphiconglyphicon-search"></i> 查询
                </button>
            </form>

            <tbody>
                <c:if test="${empty requestScope.pageInfo.list}">
                    <tr>
                        <td colspan="2">抱歉！没有查询到相关的数据！</td>
                    </tr>
                </c:if>
                <c:if test="${!empty requestScope.pageInfo.list}">
                    <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                        <tr>
                            <td>${myStatus.count}</td>
                            <td><input type="checkbox"></td>
                            <td>${admin.loginAcct}</td>
                            <td>${admin.userName}</td>
                            <td>${admin.email}</td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">
                                    <i class="glyphicon glyphicon-check"></i>
                                </button>
                                <button type="button" class="btn btn-primary btn-xs">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </button>
                                <button type="button" class="btn btn-danger btn-xs">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </button>
                            </td>
                        </tr>

                    </c:forEach>
                </c:if>
            </tbody>
        </div>

    </div>
</div>
</body>
</html>