function generateTree() {
    // 1.准备生成树形结构的JSON数据，数据的来源是发送Ajax请求得到
    $.ajax({
        "url": "menu/get/whole/tree.json",
        "type":"post",
        "dataType":"json",
        "success":function(response){
            var result = response.result;
            if(result == "SUCCESS") {
                // 2.创建JSON对象用于存储对zTree所做的设置
                var setting = {
                    view:{
                        addDiyDom:myAddDiyDom,
                        addHoverDom:myHoverDom,
                        removeHoverDom:myRemoveHoverDom
                    },
                    data:{
                        key:{
                            url:"4XX"
                        }
                    }
                };

                // 3.从响应体中获取用来生成树形结构的JSON数据
                var zNodes = response.data;

                // 4.初始化树形结构
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }

            if(result == "FAILED") {
                layer.msg(response.message);
            }
        }
    });
}


//修改默认的图标
function myAddDiyDom(treeId, treeNode) {

    //console.log("treeId="+treeId)
    //console.log(treeNode)

    // zTree生成id的规则
    // 例子：treeDemo_7_ico
    // 解析：ul标签的id_当前节点的序号_功能
    // 提示：“ul标签的id_当前节点的序号”部分可以通过访问treeNode的tId属性得到
    $("#"+treeNode.tId+"_ico").removeClass().addClass(treeNode.icon)

}
//鼠标移入时添加按钮组
function myHoverDom(treeId, treeNode) {

    // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
    // 按钮组出现的位置：节点中treeDemo_n_a超链接的后面

    // 为了在需要移除按钮组的时候能够精确定位到按钮组所在span，需要给span设置有规律的id
    var btnGroupId = treeNode.tId+"_btnGrp"

    if ($("#"+btnGroupId).length > 0) {
        return;
    }

    var editBtn = "<a id='"+treeNode.id+"'class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>"
    var removeBtn = "<a id='"+treeNode.id+"'class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>"
    var addBtn = "<a id='"+treeNode.id+"'class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'title='添加节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>"

    // 声明变量存储拼装好的按钮代码
    var btnHtml = "";
    //获取当前节点的级别
    var level = treeNode.level;
    if (level == 0) {
        btnHtml = addBtn;
    }
    if(level == 1) {
        // 级别为1时是分支节点，可以添加子节点、修改
        btnHtml= addBtn+" "+editBtn;

        if (treeNode.children.length == 0) {
            // 如果没有子节点，可以删除
            btnHtml = btnHtml+ " "+removeBtn;
        }
    }
    if(level == 2) {
        // 级别为2时是叶子节点，可以修改、删除
        btnHtml = editBtn+" "+removeBtn;
    }
    var anchorId = treeNode.tId+"_a";

    $("#"+anchorId).after("<span id='"+btnGroupId+"'>"+btnHtml+"</span>")



}

function myRemoveHoverDom(treeId, treeNode) {
    // 拼接按钮组的id
    var btnGroupId = treeNode.tId + "_btnGrp";
    // 移除对应的元素
    $("#"+btnGroupId).remove();
}