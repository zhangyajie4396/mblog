var saveFlag = true;//默认当前操作是新增
$(function () {

    $.ajax({
        type : 'get',
        url : '/admin/resource/findAll',
        dataType : "json",
        success : function(data) {
            var node = {};
            $("#treeTable").append(
                "<tr data-tt-id='1'>"+
                "<td>根目录</td>" +
                "<td>/</td>" +
                "<td>admin</td>" +
                "<td></td>" +
                "<td>" +
                "<button class='btn btn-xs btn-primary' onclick='addChild(" + JSON.stringify(node) + ")'><i class='fa fa-plus-square-o'></i> 添加下级菜单</button>"+
                "</td>" +
                "</tr>");

            $.each(data.data, function(idx, obj) {

                $("#treeTable").append(append(obj))
                var childList = obj.nodes;
                if(childList && childList.length != 0){
                    $.each(childList,function (index,node) {
                        $("#treeTable").append(append(node))
                    })
                }
            });
            var option = {
                expandLevel: 3,
                expandable: false
            };
            $('#treeTable').treetable(option);
        }
    });

    //model关闭时重置表单
    modelReset('myModal');


    $("#btn_submit").click(function () {

        var url = "/admin/resource/saveResource";
        var id =$("#id").val();
        if(id){
            saveFlag = false;
            url = "/admin/resource/updateResource";
        }
        var obj = {
            id:$('#id').val(),
            name:$('#text_name').val(),
            url:$('#text_url').val(),
            permission:$('#text_permission').val(),
            seq:$('#text_seq').val(),
            icon:$('#text_icon').val(),
            pid:$('#parentId').val()
        }
        $.ajax({
            type : 'post',
            url :url ,
            data:obj,
            success : function(data) {
                if(!data.success){
                    showMsgBox(data.msg, 5);
                    return;
                }else{
                    showMsgBox(data.msg, 4);
                    var node = data.data;
                    if(saveFlag){//如果是新增操作
                        var obj = append(node);
                        var pid = 1;
                        if(node.pid){
                            pid = node.pid;
                        };
                        //获取父节点
                        var parentNode = $("#treeTable").treetable('node',pid);
                        $("#treeTable").treetable('loadBranch',parentNode, obj);
                    }else{
                        var tr = $('tr[data-tt-id='+node.id+']');
                        var css = tr.find('td:eq(0)').find('span').css('padding-left');
                        tr.find('td:eq(0)').text('');
                        tr.find('td:eq(0)').append(
                            '<span class="indenter" style="padding-left:'+css+'"></span>'+node.text
                        )
                        // tr.find('td:eq(0)').text(node.text);
                        tr.find('td:eq(1)').text(node.url);
                        tr.find('td:eq(2)').text(node.permission);
                        tr.find('td:eq(3)').text(node.icon);
                        $("#treeTable").treetable('collapseAll');
                        $("#treeTable").treetable('expandAll');
                    }


                }
                saveFlag = true;
            }
        });
    })

});

//获取要追加的数据
function append(node) {
    var tr = "<tr data-tt-id='"+node.id+"' data-tt-parent-id='1'>";
    var url = node.url;
    var icon = node.icon;
    var btn = "<button class='btn btn-xs btn-info' onclick='editFun(" + JSON.stringify(node) + ")'><i class='fa fa-edit'></i> 修改</button>"+
        "<button class='btn btn-xs btn-primary' onclick='addChild(" + JSON.stringify(node) + ")'><i class='fa fa-plus-square-o'></i> 添加下级菜单</button>"+
        "<button class='btn btn-xs btn-warning' onClick='delFun("+JSON.stringify(node)+")'><i class='fa fa-remove'></i> 删除</button>";
    if(node.pid){
        tr = "<tr data-tt-id='"+node.id+"' data-tt-parent-id='"+node.pid+"'>";
        url = '';
        icon = '';
        btn = "<button class='btn btn-xs btn-info' onclick='editFun(" + JSON.stringify(node) + ")'><i class='fa fa-edit'></i> 修改</button>"+
            "<button class='btn btn-xs btn-warning' onClick='delFun("+JSON.stringify(node)+")'><i class='fa fa-remove'></i> 删除</button>";
    }

       var obj =  tr+
        "<td>" + node.text + "</td>" +
        "<td>"+url+"</td>" +
        "<td>"+node.permission+"</td>" +
        "<td>"+icon+"</td>" +
        "<td>" +
         btn +
        "</td>" +
        "</tr>";
    return obj;
};

//添加下级菜单
function addChild(node) {
    $("#myModalLabel").text("新增菜单");
    $('#myModal').modal();
    $("#parentId").val(node.id);
    var parentNode = 'root';
    if(node.pid){
        parentNode  = $('#'+node.pid).find('td').first().text();
    }
    $("#parentName").val(parentNode);
}

//编辑菜单
function editFun(node) {
    $("#myModalLabel").text("编辑菜单");
    $('#myModal').modal();
    $("#id").val(node.id);
    $("#parentId").val(node.pid);
    $("#text_name").val(node.text);
    $("#text_url").val(node.url);
    $("#text_icon").val(node.icon);
    $("#text_permission").val(node.permission);
    $("#text_seq").val(node.seq);
    var parentNode = 'root';
    if(node.pid){
        parentNode  = $('#'+node.pid).find('td').first().text();
    }
    $("#parentName").val(parentNode);

}

//删除菜单
function delFun(node) {
    $.DialogByZ.Confirm({Title: "", Content: "确定要删除吗?",
        FunL:function(){
            $.DialogByZ.Close();
            var arr = [];
            arr.push(node.id);
            if(node.nodes && node.nodes.length>0){
                $.each(node.nodes,function (idx,item) {
                    arr.push(item.id);
                })
            }
            $.ajax({
                type:'post',
                url:'/admin/resource/deleteByIds',
                data:{ids:arr},
                dataType:'json',
                success:function (data) {
                    if(!data.success){
                        showMsgBox(data.msg, 5);
                        return;
                    }else {
                        showMsgBox(data.msg, 4);

                        if(node.nodes && node.nodes.length>0){
                            $("#treeTable").treetable('removeNode',node.id);
                        }else{
                            $('tr[data-tt-id='+node.id+']').remove();
                        }
                    }
                }
            })
        },
        FunR:function(){
            $.DialogByZ.Close();
        }})
}