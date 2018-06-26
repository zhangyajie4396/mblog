function addRoleFun() {
    $("#myModalLabel").text("新增");
    $('#myModal').modal();
}

function batchDelFun() {
    var selections = $("#table").bootstrapTable('getSelections');
    if(selections.length == 0){
        showBox("请至少选一条记录",1);
        return;
    }
    var ids = [];
    for(var i=0;i<selections.length;i++){
        ids.push(selections[i].id);
    }
    $.ajax({
        type : 'post',
        url :'/admin/role/deleteByIds' ,
        data:{
            ids:ids
        },
        success : function(data) {
            if(data.success){
                showBox(data.msg, 4);
                $("#table").bootstrapTable('refresh');
            }else{
                showBox(data.msg, 5);
            }
        }
    });

};

function editRoleFun(row) {
    $("#myModalLabel").text("编辑");
    $('#myModal').modal();
    $('#id').val(row.id);
    $('#roleName').val(row.roleName);
    $('#remark').val(row.remark);
};


//权限树弹窗
function permissionConfigFun(row) {
    $('#rowId').val(row.id);
    var unselections = $('#tree').treeview('getUnselected');
    var resources = row.resources;
    var ids = [];

    for(var i=0;i<resources.length;i++){
        var id = resources[i].id;
        for(var j=0;j<unselections.length;j++){
            var nodeid = unselections[j].id;
            if(id == nodeid){
                ids.push(unselections[j].nodeId);
                break;
            }
        }
    }

    $('#treeModal').modal();
    $('#tree').treeview('checkNode', [ids, { silent: true }]);
}

//初始化加载tree
function loadTree() {

    $.ajax({
       type : 'post',
       url :'/admin/resource/findAll' ,
       success : function(data) {
           if(data.success){
               $('#tree').treeview({
                   data: data.data,//节点数据
                   showCheckbox:true,
                   multiSelect: true,
                   onhoverColor: "#F5F5F5", //光标停在节点上激活的默认背景色      String
                   onNodeChecked:function (event,node) {

                       if(!node.pid){//父级
                           var nodes = node.nodes;
                           if(nodes.length != 0){
                               var nodeIds =[];
                               for(var i=0;i<nodes.length;i++){
                                   nodeIds.push(nodes[i].nodeId)
                               }
                            $('#tree').treeview('checkNode', [nodeIds, { silent: true }]);
                           }
                       }else{
                           $('#tree').treeview('checkNode', [node.parentId, { silent: true }]);
                       }
                   },
                   onNodeUnchecked:function (event,node) {
                       if(!node.pid){//父级
                           var nodes = node.nodes;
                           if(nodes.length != 0){
                               var nodeIds =[];
                               for(var i=0;i<nodes.length;i++){
                                   nodeIds.push(nodes[i].nodeId)
                               }
                               $('#tree').treeview('uncheckNode', [nodeIds, { silent: true }]);
                           }
                       }else{
                           $('#tree').treeview('uncheckNode', [node.nodeId, { silent: true }]);
                            var parentNode = $('#tree').treeview('getNode',node.parentId);
                            var nodes = parentNode.nodes;
                            var flag = true;//是否全部展开标志
                            for(var i =0;i<nodes.length;i++){

                                if(nodes[i].state.checked){
                                    flag = false;
                                    break;
                                }
                            }

                            if(flag){
                                $('#tree').treeview('uncheckNode', [node.parentId, { silent: true }]);
                            }
                       }
                   }
               });
           }
       }
   });

    return tree;
}

$(function(){
    $('#myModal').on('show.bs.modal', function() {
        document.getElementById("form").reset();
        $('#tree').treeview('uncheckAll');
    });
    $('#treeModal').on('show.bs.modal', function() {
        $('#tree').treeview('uncheckAll');
    });


    //新增、修改角色
    $("#btn_submit").click(function () {

        var url = "/admin/role/saveRole";
        var id =$("#id").val();
        if(id){
            url = "/admin/role/updateRole";
        }

        $.ajax({
            type : 'post',
            url :url ,
            data:$("form").serialize(),
            success : function(data) {
                if(!data.success){
                    showBox(data.msg, 5);
                    return;
                }else{
                    showBox(data.msg, 4);
                    $("#table").bootstrapTable('refresh');
                }
            }
        });
    });

    //权限配置
    $("#btn_permission").click(function () {
        var selections = $('#tree').data('treeview').getChecked();
        // if(selections.length == 0){
        //     showBox('请选择记录',5);
        //     return;
        // }

        var arr = [];
        if(selections.length>0){
            for(var i=0;i<selections.length;i++){
                var obj = {
                    roleId:$('#rowId').val(),
                    resourceId:selections[i].id
                };
                arr.push(obj);

            }
        }else{
            arr.push(
                {roleId:$('#rowId').val()}
            )
        }


        $.ajax({
            type : 'post',
            url :'/admin/role/permissionConfig' ,
            data:JSON.stringify(arr),
            headers:{"Content-Type":"application/json;charset=utf-8"},
            success : function(data) {
                if(!data.success){
                    showBox(data.msg, 5);
                    return;
                }else{
                    showBox(data.msg, 4);
                    $("#table").bootstrapTable('refresh');
                }
            }
        });
    });


    //加载tree
   loadTree();


});


