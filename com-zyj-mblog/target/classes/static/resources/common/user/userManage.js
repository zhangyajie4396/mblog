function addUserFun() {
    $("#myModalLabel").text("新增");
    $('#myModal').modal();
}

function batchDelFun() {
    var selections = $("#table").bootstrapTable('getSelections');
    if(selections.length == 0){
        showMsgBox("请至少选一条记录",1);
        return;
    }
    var ids = [];
    for(var i=0;i<selections.length;i++){
        ids.push(selections[i].id);
    }
    $.ajax({
        type : 'post',
        url :'/admin/user/deleteByIds' ,
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

}

function editUserFun(row) {
    $("#myModalLabel").text("编辑");
    $('#myModal').modal();
    $('#id').val(row.id);
    $('#username').val(row.username);
    $('#nickname').val(row.nickname);


    var roles = row.roles;
    var arr = [];
    $.each(roles,function (index,data) {
        arr.push(data.id)
    })
    $('.selectpicker').selectpicker('val', arr);
}

function editStatusFun(row) {
    var status = row.disabled == 1 ? 0 : 1;
    var obj = {
        id:row.id,
        disabled:status
    }
    $.ajax({
        type : 'post',
        url : "/admin/user/doDisable",
        data:obj,
        dataType:'json',
        success : function(data) {//返回list数据并循环获取
            if(!data.success){
                showMsgBox(data.msg, 5);
                return;
            }else{
                showMsgBox(data.msg, 4);
                $("#table").bootstrapTable('refresh');
            }
        }
    });
}

$(function(){

    $("#table").bootstrapTable({ // 对应table标签的id
        url: "/admin/user/selectList", // 获取表格数据的url
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        toolbar:'#toolbar',
        search:true,
        showRefresh:true,
        // pagination: true, // 在表格底部显示分页组件，默认false
        // pageList: [10, 25, 50, 100], // 设置页面可以显示的数据条数
        // pageSize: 10, // 页面数据条数
        // pageNumber: 1, // 首页页码
        // sidePagination: 'client', // 设置为服务器端分页
        /* queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

             return {
                 pageSize: params.limit, // 每页要显示的数据条数
                 offset: params.offset, // 每页显示数据的开始行号
                 sort: params.sort, // 要排序的字段
                 sortOrder: params.order, // 排序规则
                 dataId: $("#dataId").val() // 额外添加的参数
             }
         },*/
        // sortName: 'id', // 要排序的字段
        // sortOrder: 'desc', // 排序规则
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
                field: 'username', // 返回json数据中的name
                title: '用户名', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'nickname', // 返回json数据中的name
                title: '昵称', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'disabled',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter:function (value) {
                    if(value ==1){
                        return  '启用';
                    }else if(value == 0){
                        return '停用';
                    }
                    return '-';
                }
            },{
                field: 'roles',
                title: '角色',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var role = '';
                    if(value && value.length > 0){
                        for(var i=0;i<value.length;i++){
                            if(i==value.length-1){
                                role += value[i].name
                            }else{
                                role += value[i].name+','
                            }

                        }
                    };
                    return role;
                }
            },{
                field: 'createTime', // 返回json数据中的name
                title: '注册时间', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                formatter:function(value){
                    return changeDateFormat(value);
                }
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn =  "<button class='btn btn-default' onclick='editUserFun(" + JSON.stringify(row) + ")'>编辑</button>";
                    var status = '停用';
                    if(row.disabled ==0){
                        status = '启用';
                    }
                    btn += "<button class='btn btn-default' onclick='editStatusFun(" + JSON.stringify(row) + ")'>"+status+"</button>";
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


    $(".selectpicker").selectpicker({
        noneSelectedText : '请选择'
    });

    $(window).on('load', function() {
        $('.selectpicker').selectpicker('val', '');
        $('.selectpicker').selectpicker('refresh');
    });
    //下拉数据加载
    $.ajax({
        type : 'get',
        url : "/admin/role/selectList",
        dataType : 'json',
        success : function(datas) {//返回list数据并循环获取
            var data = datas.data;
            var select = $("#txt_role");
            for (var i = 0; i < data.length; i++) {
                select.append("<option value='"+data[i].id+"'>"
                    + data[i].name + "</option>");
            }
            $('.selectpicker').selectpicker('val', '');
            $('.selectpicker').selectpicker('refresh');
        }
    });


    $('#myModal').on('show.bs.modal', function() {
        document.getElementById("form").reset();
        $('.selectpicker').selectpicker('val', '');

    });
    
    $("#btn_submit").click(function () {

        var url = "/admin/user/save";
        var id =$("#id").val();
        if(id){
            url = "/admin/user/update";
        }

        var roleIds = $("#txt_role").val();
        var roles = [];
        if(roleIds.length>0){
            $.each(roleIds,function (item,data) {

                roles.push({id:data})
            })
        }

        var user = {
            username:$("#username").val(),
            nickname:$("#nickname").val(),
            password : $("#password").val(),
            id:id,
        };
        var jsonData = {
            user:user,
            roles:roles
        };


        $.ajax({
            type : 'post',
            url :url ,
            data:{jsonData:JSON.stringify(jsonData)},
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


