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
    <link rel="stylesheet" href="css/pagination.css">
    <script type="text/javascript" src="jquery/jquery.pagination.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {

            //初始化函数 对页码和导航条进行初始化操作 确定要删除此管理员吗？
            initPagination();
            function initPagination() {
                var totalRecord = ${requestScope.pageInfo.total};

                var properties = {
                    //边缘页数
                    num_edge_entries: 3,
                    //主体页数
                    num_display_entries: 5,
                    //点击翻页按钮之后的回调函数
                    callback: pageSelectCallback,
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
                var pageNum = pageIndex + 1;
                //页面跳转
                window.location.href = "admin/get/page.html?pageNum=" + pageNum + "&keyword=${param.keyword}";
                //取消当前超链接的默认行为
                return false;


            }

            //复选框的全选、全不选
            $("#checkAll").click(function (){
                $(".delCheck").prop("checked", $("#checkAll").prop("checked"));

            })
            $(document).on("click", ".delCheck", function () {
                var flag = $(".delCheck:checked").length == $(".delCheck").length;
                $("#checkAll").prop("checked", flag);
            })


            //单个删除提示
            $(".glyphicon-remove").click(function () {

                return confirm("确定要删除此管理员吗？")
            })

            //删除多个管理员
            $("#delBtn").click(function () {
                var list  = "";
                var count = 0;

                //获取要删除的id 和删除的个数
                $(".delCheck").each(function () {
                    if($(this).is(':checked')){
                        list+=$(this).val()+",";
                        count++;
                    }else{}
                })

                if (!confirm("确定要删除这"+count+"个管理员吗？")) {
                    return;
                }
                else{
                    console.log(list);
                    $.ajax({
                        url: "admin/remove/admins.json",
                        type:"post",
                        data:{
                            "ids": list,
                        },
                        success:function(data) {
                            console.log(data);
                            layer.msg(data, {time: 1000}, function () {
                                location.reload();
                            });
                        }
                    })
                }
            })
        });
    </script>
</head>
<body>

<%@include file="/WEB-INF/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <%-- 这里显示主题内容  --%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form action="admin/get/page.html" method="post" class="form-inline" role="form"
                          style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件" value="${param.keyword}">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>

                    <%--                    <button type="button" class="btn btn-primary"--%>
                    <%--                            style="float: right;" onclick="window.location.href='add.html'">--%>
                    <%--                        <i class="glyphicon glyphicon-plus"></i> 新增--%>
                    <%--                    </button>--%>
                    <a href="admin/to/add/page.html" class="btn btn-primary" style="float: right; margin-left: 10px;"><i
                            class="glyphicon glyphicon-plus"></i> 新增</a>
                    <button id="delBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="60px">序号</th>
                                <th width="30"><input type="checkbox" id="checkAll"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉！没有查询到相关的数据！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <c:if test="${admin.id ==  sessionScope.loginAdmin.id}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${admin.id !=  sessionScope.loginAdmin.id}">
                                            <td><input type="checkbox" value="${admin.id}" class="delCheck" ></td>
                                        </c:if>

                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs" width="20px"
                                                    height="20px">
                                                <i class="glyphicon glyphicon-check"></i>
                                            </button>

                                            <a href="admin/to/edit/page.html?adminId=${admin.id }&pageNum=${requestScope.pageInfo.pageNum }&keyword=${param.keyword }"
                                               class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></a>


                                            <c:if test="${sessionScope.loginAdmin.id != admin.id}">
                                                <a href="admin/remove/${admin.id }/${requestScope.pageInfo.pageNum }/${param.keyword }.html"
                                                   class="btn btn-danger btn-xs"><i
                                                        class=" glyphicon glyphicon-remove"></i></a>
                                            </c:if>

                                        </td>
                                    </tr>

                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>

                </div>
            </div>
</body>
</html>