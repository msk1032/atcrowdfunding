$(function () {

  //显示添加窗口
  $("#showAddModalBtn").click(function () {
    $("#addModal").modal("show");
  })

  /**
   * 添加模块
   */
  $("#saveRoleBtn").click(function () {
    var roleName = $.trim($("#addModal [name=roleName]").val());

    $.ajax({
      url : "role/save.json",
      data:{"name":roleName},
      type:"post",
      success:function (res) {
        var result = res.result;
        if(result == "SUCCESS") {
          layer.msg("操作成功！", {time: 500}, function () {
            window.location.href="role/get/page.html?pageNum="+9999999;
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
  })
  //关闭模态框
  $("#addModal").modal("hide");
  //清除文本框的值
  $("#addModal [name=roleName]").val("");

  /**
   * 修改模块
   */

  $(".updateClass").click(function () {
    $("#editModal").modal("show");
    //给修改模态框的隐藏文本框赋值 将roleId传过去
    $("#roleId").val($(this).attr("roleId"));
    //回显要修改的角色名
    $("#editModal [name=roleName]").val($(this).parent().prev().text())

  })

  $("#updateRoleBtn").click(function () {

    console.log($("#roleId").val());
    $.ajax({
      url: "role/update.json",
      data:{
        "id": $("#roleId").val(),
        "name": $("#editModal [name=roleName]").val()
      },
      type:"post",
      success:function (res) {
        var result = res.result;
        if(result == "SUCCESS") {
          layer.msg("操作成功！", {time: 500}, function () {
            location.reload();
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
  })
  //关闭模态框
  $("#editModal").modal("hide");
  //清除文本框的值
  $("#editModal [name=roleName]").val("");

  //删除模块

  //复选框的全选、全不选
  $("#checkAll").click(function (){
    $(".delCheck").prop("checked", $("#checkAll").prop("checked"));
  })
  $(document).on("click", ".delCheck", function () {
    var flag = $(".check:checked").length == $(".delCheck").length;
    $("#checkAll").prop("checked", flag);
  })

  $(".delRoleClass").click(function () {
    var delRoleIdList = new Array();
    delRoleIdList.push($(this).attr("roleId"));
    if (confirm("确定要删除吗？")) {
      console.log(delRoleIdList);
      window.location.href="role/remove.html?list="+delRoleIdList
          +"&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}";
    }

  })

  $("#delRoleBtn").click(function () {
    var count = 0;
    var delRoleIdList = new Array();
    $(".delCheck").each(function () {
      if (this.checked == true) {
        delRoleIdList.push($(this).val());
        count++;
      }
    })
    console.log(count);
    console.log(delRoleIdList);
    if (confirm("确定要删除吗？")) {
      window.location.href="role/remove.html?list="+delRoleIdList
          +"&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}";
    }
  })


  $(".checkBtn").click(function () {

    //把点击按钮当前的roleId 设置为全局变量
    window.roleId = $(this).attr("roleId");

    $("#assignModal").modal("show");

    //在模态框中显示权限的树形结构
    fillAuthTree()
  })

  //执行分配
  $("#assignBtn").click(function () {
    //收集被勾选的节点
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    var authIdArray = [];

    var checkedNodes = zTreeObj.getCheckedNodes()

    for(var i = 0; i < checkedNodes.length; i++) {
      authIdArray.push(checkedNodes[i].id);
    }

    var requestBody = {
      "authIdArray":authIdArray, // 为了服务器端 handler 方法能够统一使用 List<Integer>方式接收数据，roleId 也存入数组
      "roleId":[window.roleId]
    };
    requestBody = JSON.stringify(requestBody);

    //发送请求保存数据
    $.ajax({
      url:"assign/do/role/assign/auth.json",
      type:"post",
      //data:requestBody,
      data:{
        "roleId":"1",
      },
      "contentType":"application/json;charset=UTF-8",
      "success":function(response){
        var result = response.result;
        if(result == "SUCCESS") {
          layer.msg("操作成功！");
        }
        if(result == "FAILED") {
          layer.msg("操作失败！"+response.message);
        }
      },
      "error":function(response) {
        layer.msg(response.status+" "+response.statusText);
      }

    })

    $("#assignModal").modal("hide");
  })


})

//获取要在模态框中选择的函数
function fillAuthTree() {

  var ajaxReturn = $.ajax({
    "url":"assign/get/all/auth.json",
    "type":"post",
    "dataType":"json",
    "async":false
  });

  if(ajaxReturn.status != 200) {
    layer.msg("请求处理出错！响应状态码是："+ajaxReturn.status+" 说明是："+ajaxReturn.statusText);
    return ;
  }

  // 2.从响应结果中获取Auth的JSON数据
  // 从服务器端查询到的list不需要组装成树形结构，这里我们交给zTree去组装
  var authList = ajaxReturn.responseJSON.data;

  // 3.准备对zTree进行设置的JSON对象
  var setting = {
    "data": {
      "simpleData": {

        // 开启简单JSON功能
        "enable": true,

        // 使用categoryId属性关联父节点，不用默认的pId了
        "pIdKey": "categoryId"
      },
      "key": {
        // 使用title属性显示节点名称，不用默认的name作为属性名了
        "name": "title"
      }
    },
    "check": {
      "enable": true
    }
  };

  $.fn.zTree.init($("#authTreeDemo"), setting, authList);

  // 获取zTreeObj对象
  var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

  // 调用zTreeObj对象的方法，把节点展开
  zTreeObj.expandAll(true);

  // 5.查询已分配的Auth的id组成的数组
  ajaxReturn = $.ajax({
    "url":"assign/get/assigned/auth/id/by/role/id.json",
    "type":"post",
    "data":{
      "roleId":window.roleId
    },
    "dataType":"json",
    "async":false
  });

  if(ajaxReturn.status != 200) {
    layer.msg("请求处理出错！响应状态码是："+ajaxReturn.status+" 说明是："+ajaxReturn.statusText);
    return ;
  }

  // 从响应结果中获取authIdArray
  var authIdArray = ajaxReturn.responseJSON.data;

  // 6.根据authIdArray把树形结构中对应的节点勾选上
  for(var i = 0; i < authIdArray.length; i++) {

    var treeNode = zTreeObj.getNodeByParam("id", authIdArray[i]);

    //true 表示选中 false表示不联动
    zTreeObj.checkNode(treeNode, true, false);
  }
}




