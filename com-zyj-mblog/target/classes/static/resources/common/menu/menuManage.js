$(function () {

    $.ajax({
        type : 'get',
        url : '/admin/resource/findAll',
        dataType : "json",
        success : function(data) {
            $.each(data.data, function(idx, obj) {
                append(obj)
                var childList = obj.nodes;
                if(childList && childList.length != 0){
                    $.each(childList,function (index,node) {
                        append(node)
                    })
                }



            });
            var option = {
                expandLevel: 2,
            };
            $('#treeTable').treeTable(option);
        }
    });


});

function append(node) {
    var tr = "<tr id='"+node.id+"'>";
    var url = node.url;
    if(node.pid){
        tr = "<tr id='"+node.id+"' pid='"+node.pid+"'>";
        url = '';
    }
    $("#treeTable").append(
        tr+
        "<td>" + node.text + "</td>" +
        "<td>"+url+"</td>" +
        "<td>" +
        " <a href='${base}/admin/authMenus/view?id=${row.id}' class='btn btn-xs btn-primary'>"+
        "<i class='fa fa-edit'></i> 修改</a>" +
        " <a href='${base}/admin/authMenus/view?id=${row.id}' class='btn btn-xs btn-primary'>"+
        "<i class='fa fa-plus-square-o'></i> 添加下级菜单</a>" +
        " <a href='${base}/admin/authMenus/view?id=${row.id}' class='btn btn-xs btn-warning'>"+
        "<i class='fa fa-remove'></i> 删除</a>" +
        "</td>" +
        "</tr>");
}