<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/6
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/head.jsp"%>

<body>
<%@ include file="/WEB-INF/nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/to/main/page.html">首页</a></li>
                <li><a href="/admin/get/page.html">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">
                    表单数据
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <form action="admin/update.html" method="post" role="form">
                        <p>${requestScope.exception.message }</p>
                        <input type="hidden" name="id" value="${param.adminId}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="loginAcct">登录账号</label>
                            <input
                                    name="loginAcct"
                                    type="text" class="form-control" id="loginAcct"
                                    placeholder="请输入登录账号"
                                    value="${admin.loginAcct}"
                                    disabled="disabled"
                            />
                        </div>

                        <div class="form-group">
                            <label for="exampleInputPasswad1">用户昵称</label>
                            <input
                                    name="userName"
                                    type="text" class="form-control" id="exampleInputPasswad1"
                                    placeholder="请输入用户昵称"
                                    value="${admin.userName}"
                            >
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input type="email"
                                   name="email"
                                   class="form-control" id="exampleInputEmail1"
                                   placeholder="请输入邮箱地址"
                                   value="${admin.email}"
                            >
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
                                xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success">
                            <i class="glyphicon glyphicon-plus"></i> 修改
                        </button>
                        <button type="reset" class="btn btn-danger">
                            <i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
