<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/12/8
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" role="form">
                    <div class="form-group has-success has-feedback">
<%--                        添加隐藏文本框 将roleId值传过去--%>
                        <input type="hidden" id="roleId">
                        <input
                                type="text" name="roleName"
                                class="form-control" placeholder="请输入角色名称"
                                autofocus>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="updateRoleBtn" type="button" class="btn btn-success">修改</button>
            </div>
        </div>
    </div>
</div>
