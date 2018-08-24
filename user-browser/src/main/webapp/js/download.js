$(function () {
    showCode();
});

//接收用户信息加载完成事件消息
$(document).on("click", "#userdata_Tip", function () {
    show_user_bar();
});

/*显示二维码*/
function showCode() {
    var str = _settings.gobangDown;
    $("#code").empty();
    //$('#code').qrcode(str);
    $("#code").qrcode({
        render: "table",
        text: str
    });
    //设置下载链接
    $("#downLink").attr("href", str);
    $("#downJDK").attr("href", _settings.jdkDown);
}

function toUtf8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        }
    }
    return out;
}