function addRoleFun() {
    $("#myModalLabel").text("新增");
    $('#myModal').modal();
}

function batchDelFun() {
    var selections = $("#table").bootstrapTable('getSelections');
    if(selections.length == 0){
        showMsgBox("请至少选一条记录",1);
        return;
    }
    $.DialogByZ.Confirm({Title: "", Content: "确定要删除吗?",
        FunL:function(){
            $.DialogByZ.Close();
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
                        showMsgBox(data.msg, 4);
                        $("#table").bootstrapTable('refresh');
                    }else{
                        showMsgBox(data.msg, 5);
                    }
                }
            });
        },
        FunR:function(){
            $.DialogByZ.Close();
        }})


};

function editRoleFun(row) {
    $("#myModalLabel").text("编辑");
    $('#myModal').modal();
    $('#id').val(row.id);
    $('#roleName').val(row.code);
    $('#remark').val(row.name);
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
                           if(nodes && nodes.length != 0){
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
                           if(nodes && nodes.length != 0){
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


    $("#table").bootstrapTable({ // 对应table标签的id
        url: "/admin/role/selectList", // 获取表格数据的url
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        toolbar:'#toolbar',
        search:true,
        showRefresh:true,
        columns: [
            {
                checkbox : true,
                align : 'center'
            },
            {
                field: 'id', // 返回json数据中的name
                title: '角色id', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            },
            {
                field: 'code', // 返回json数据中的name
                title: '角色编码', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'name',
                title: '角色名称',
                align: 'center',
                valign: 'middle'
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 200, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn =  "<button class='btn btn-xs btn-info' onclick='editRoleFun(" + JSON.stringify(row) + ")'><i class='fa fa-edit'></i>编辑</button>";
                    btn +="<button class='btn btn-xs btn-info' onclick='permissionConfigFun(" + JSON.stringify(row) + ")'><i class='fa fa-key'></i>权限配置</button>";
                    return btn;
                }
            }
        ],
        onLoadSuccess: function(){  //加载成功时执行

        },
        onLoadError: function(){  //加载失败时执行

        }

    });
    $('#table').bootstrapTable('hideColumn', 'id');


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
                    showMsgBox(data.msg, 5);
                    return;
                }else{
                    showMsgBox(data.msg, 4);
                    $("#table").bootstrapTable('refresh');
                }
            }
        });
    });

    //权限配置
    $("#btn_permission").click(function () {
        var selections = $('#tree').data('treeview').getChecked();
        // if(selections.length == 0){
        //     showMsgBox('请选择记录',5);
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
                    showMsgBox(data.msg, 5);
                    return;
                }else{
                    showMsgBox(data.msg, 4);
                    $("#table").bootstrapTable('refresh');
                }
            }
        });
    });


    //加载tree
   loadTree();


});


