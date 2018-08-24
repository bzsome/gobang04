var user_div = "#user-table"

//接收用户信息加载完成事件消息
$(document).on("click", "#userdata_Tip", function () {
    show_user_bar();
    show_user_center();
});

function show_user_center() {
    if (!isLoginAndShow()) {
        return;
    }
    $(user_div).find("#username").text(userData.username);
    $(user_div).find("#email").text(userData.email);
    $(user_div).find("#sign").text(userData.sign);
    //使用时间格式化插件格式化时间
    var time = new Date().Format('YYYY-MM-DD HH:mm:ss');
    $(user_div).find("#reg_time").text(time);
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "D+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(Y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}