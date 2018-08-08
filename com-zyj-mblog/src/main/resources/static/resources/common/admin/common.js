//弹窗提示
function showMsgBox(msg,type){
    var tip = msg;
    switch (type){
        case 1:
            tip = tip?tip:"服务器繁忙，请稍后再试。";
            break;
        case 4:
            tip = tip?tip:"设置成功！";
            break;
        case 5:
            tip = tip?tip:"操作失败";
            break;
        case 6:
            tip = tip?tip:"正在加载中，请稍后...";
    }

    ZENG.msgbox.show(tip, type, 3000);

}

//时间戳转时间 年-月-日
function changeDateFormat(cellval) {
    var dateVal = cellval + "";
    if (cellval != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
}


//model重置
function modelReset(selector) {
    $('#'+selector).on('show.bs.modal', function() {
        document.getElementById("form").reset();

    });
};

//按钮
function getBtn(opt) {
    var btn = "<button class='btn btn-xs btn-"+opt.class+"' " +
        "onclick='" +opt.fun(JSON.stringify(opt.data)) + "'>" +
        "<i class='fa fa-"+opt.i+"'></i> "+opt.text+"</button>";
    return btn;
}
