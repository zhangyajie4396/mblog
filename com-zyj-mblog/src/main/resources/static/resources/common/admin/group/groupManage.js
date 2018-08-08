function addGroupFun() {
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
                url :'/admin/group/deleteByIds' ,
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


}

function editGroupFun(row) {
    $("#myModalLabel").text("编辑");
    $('#myModal').modal();
    $('#id').val(row.id);
    $('#code').val(row.code);
    $('#name').val(row.name);
    $('#status').val(row.status);
    $('.selectpicker').selectpicker('val', $('#status').val());
}


$(function(){

    $("#table").bootstrapTable({ // 对应table标签的id
        url: "/admin/group/selectList", // 获取表格数据的url
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
                title: '用户id', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            },
            {
                field: 'code', // 返回json数据中的name
                title: '编码', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'name', // 返回json数据中的name
                title: '名称', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'status',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter:function (value) {
                    if(value ==1){
                        return  '显示';
                    }else if(value == 0){
                        return '隐藏';
                    }
                    return '-';
                }
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn =  "<button class='btn btn-xs btn-info' onclick='editGroupFun(" + JSON.stringify(row) + ")'><i class='fa fa-edit'></i>编辑</button>";
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
    $(".selectpicker").selectpicker();
    //model关闭时重置表单
    modelReset('myModal');


    $("#btn_submit").click(function () {

        var url = "/admin/group/save";
        var id =$("#id").val();
        if(id){
            url = "/admin/group/update";
        }

        var group = {
            code:$("#code").val(),
            name:$("#name").val(),
            status:$("#status").val(),
            id:id,
        };


        $.ajax({
            type : 'post',
            url :url ,
            data:group,
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
    })
})


