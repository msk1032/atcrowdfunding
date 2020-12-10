<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/7
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/head.jsp" %>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" href="css/pagination.css">
    <link rel="stylesheet" href="ztree/zTreeStyle.css">
    <script type="text/javascript" src="jquery/jquery.pagination.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="crowdjs/my-role.js"></script>
    <script type="text/javascript">
        $(function () {
            initPagination();

            function initPagination() {
                var total = ${requestScope.pageInfo.total};

                var properties = {
                    num_edge_entries: 3,
                    num_display_entries: 5,
                    callback: pageSelectCallback,
                    current_page: ${requestScope.pageInfo.pageNum-1},
                    prev_text: "上一页",
                    next_text: "下一页",
                    items_per_page: ${requestScope.pageInfo.pageSize},
                }

                //显示出分页的按钮

                $("#Pagination").pagination(total, properties);

            }

            function pageSelectCallback(pageIndex, jQuery) {
                var pageNum = pageIndex + 1;

                window.location.href = "role/get/page.html?pageNum=" + pageNum + "&keyword=${param.keyword}";
                //取消当前超链接的默认行为
                return false;
            }

        })
    </script>
</head>
<body>

<%@include file="/WEB-INF/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;"
                          action="role/get/page.html" method="post">

                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword"
                                       id="keywordInput"
                                       value="${param.keyword}"
                                       class="form-control has-success"
                                       type="text"
                                       placeholder="请输入查询条件"
                                >
                            </div>
                        </div>
                        <button id="searchBtn" type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="delRoleBtn"  type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button
                            type="button"
                            id="showAddModalBtn" class="btn btn-primary"
                            style="float: right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="checkAll" type="checkbox" ></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                            <tr>
                                <td colspan="4" align="center">抱歉！没有查询到相关的数据！</td>
                            </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                            <c:forEach items="${requestScope.pageInfo.list}" var="role" varStatus="myStatus">
                            <tr>
                                <td>${myStatus.count}</td>
                                <td><input type="checkbox" width="30" class="delCheck" value="${role.id}"></td>
                                <td>${role.name}</td>
                                <td>
                                    <button type="button" roleId="${role.id}" class="btn btn-success btn-xs checkBtn"><i
                                            class=" glyphicon glyphicon-check"></i></button>
<%--                                    给修改和删除按钮添加自定义属性 发送请求时将role的id传过去--%>
                                    <button type="button" roleId="${role.id}" class="btn btn-primary btn-xs updateClass"><i
                                            class=" glyphicon glyphicon-pencil "></i></button>
                                    <button type="button" roleId="${role.id}" class="btn btn-danger btn-xs delRoleClass"><i
                                            class=" glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            </c:forEach>

                            </c:if>
                            <tfoot>
                            <tr>
                                <td colspan="4" align="center">
                                    <div id="Pagination" class="pagination"></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</div>
</div>
<%@include file="/WEB-INF/modal-role-add.jsp"%>
<%@include file="/WEB-INF/modal-role-edit.jsp"%>
<%@include file="/WEB-INF/modal-role-assign-auth.jsp"%>
</body>
</html>
