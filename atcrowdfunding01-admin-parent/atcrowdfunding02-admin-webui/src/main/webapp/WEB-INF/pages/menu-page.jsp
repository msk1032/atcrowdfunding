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
<head>
    <link rel="stylesheet" href="static/ztree/zTreeStyle.css">
    <script type="text/javascript" src="static/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="static/crowdjs/my-menu.js"></script>
    <script type="text/javascript" src="static/layer/layer.js"></script>
    <script>
        $(function(){

            generateTree();

            //添加节点
            $("#treeDemo").on("click",".addBtn",function () {
                //将当前节点的id作为新节点的pid
                window.pid = this.id;
                $("#menuAddModal").modal("show");

                return false;
            })

            //更新节点
            $("#treeDemo").on("click", ".editBtn", function () {
                window.id = this.id;
                $("#menuEditModal").modal("show");

                //回显表单信息
                var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var currentNode = zTreeObj.getNodeByParam("id", window.id);

                $("#menuEditModal [name=name]").val(currentNode.name);
                $("#menuEditModal [name=url]").val(currentNode.url);
                //设置value要将值放入[]中
                $("#menuEditModal [name=icon]").val([currentNode.icon]);

                return false;
            })

            //删除节点
            $("#treeDemo").on("click", ".removeBtn", function () {
                window.id = this.id;
                $("#menuConfirmModal").modal("show");

                //获取当前节点信息
                var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var currentNode = zTreeObj.getNodeByParam("id", window.id);

                $("#removeNodeSpan").html("【<i class='"+currentNode.icon+"'></i>"+currentNode.name+"】");

                return false;

            })

            //添加节点 点击保存按钮
            $("#menuSaveBtn").click(function () {
                $.ajax({
                    url: "menu/save.json",
                    type: "post",
                    data:{
                        "pid": window.pid,
                        "url": $.trim($("#menuAddModal [name=url]").val()),
                        "name": $.trim($("#menuAddModal [name=name]").val()),
                        "icon": $("#menuAddModal [name=icon]:checked").val()
                    },
                    success:function (res) {
                        var result = res.result;
                        if(result == "SUCCESS") {
                            layer.msg("操作成功！", {time: 500}, function () {
                                generateTree();
                            });
                        }

                        if(result == "FAILED") {
                            layer.msg("操作失败！", {time: 500}, function () {
                            });
                        }

                    },
                    error:function (res) {
                        layer.msg(res.status+" "+res.statusText, {time: 500}, function () {
                        });
                    }
                })
                $("#menuAddModal").modal("hide");
                //清空表单
                $("#menuResetBtn").click();

            })

            //更新节点点击保存按钮
            $("#menuEditBtn").click(function () {

                console.log($.trim($("#menuEditModal [name=name]").val()));
                $.ajax({
                    url: "menu/update.json",
                    type: "post",
                    data:{
                        "id": window.id,
                        "url": $.trim($("#menuEditModal [name=url]").val()),
                        "name": $.trim($("#menuEditModal [name=name]").val()),
                        "icon": $("#menuEditModal [name=icon]:checked").val()
                    },
                    success:function (res) {
                        var result = res.result;
                        if(result == "SUCCESS") {
                            layer.msg("操作成功！", {time: 500}, function () {
                                generateTree();
                            });
                        }

                        if(result == "FAILED") {
                            layer.msg("操作失败！", {time: 500}, function () {
                            });
                        }

                    },
                    error:function (res) {
                        layer.msg(res.status+" "+res.statusText, {time: 500}, function () {
                        });
                    }
                });

                $("#menuEditModal").modal("hide");
            })

            $("#confirmBtn").click(function () {
                $.ajax({
                    url: "menu/remove.json",
                    type:"post",
                    data:{
                        "id":window.id
                    },
                    success:function (res) {
                        var result = res.result;
                        if(result == "SUCCESS") {
                            layer.msg("操作成功！", {time: 500}, function () {
                                generateTree();
                            });
                        }

                        if(result == "FAILED") {
                            layer.msg("操作失败！", {time: 500}, function () {
                            });
                        }

                    },
                    error:function (res) {
                        layer.msg(res.status+" "+res.statusText, {time: 500}, function () {
                        });
                    }

                })
                //关闭模态框 清空消息
                $("#menuConfirmModal").modal("hide")
                $("#removeNodeSpan").html("")
            })



        });
    </script>
</head>
<body>

<%@include file="/WEB-INF/common/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <%-- 这里显示主题内容  --%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>

    </div>
</div>
<%@include file="/WEB-INF/modal/modal-menu-add.jsp"%>
<%@include file="/WEB-INF/modal/modal-menu-edit.jsp"%>
<%@include file="/WEB-INF/modal/modal-menu-confirm.jsp"%>
</body>
</html>