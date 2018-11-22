/*
用户登录相关
 */

var login_submit = "#login_btn";//登录按钮

$(function () {
    ajaxLogin();
});

$(login_submit).click(function (event) {
    event.preventDefault();
    submit_disenable();
    ajaxLogin();
});

function ajaxLogin() {
    $.ajax({
        type: "post",
        url: _settings.api.login,
        data: "username=chaoa&password=123456",
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //登陆成功
                login_success(result);
            } else {
                login_error();
            }
        },
        error: function () {
            showNet_error();
        },
        complete: function () {
            // Handle the complete event
            submit_enable();
        }
    });
}

var classes = "";//提交按钮新class
var oldClasses = "";//提交按钮原class
function submit_disenable() {
    oldClasses = $(login_submit).attr("class");
    classes = oldClasses + " disabled";
    $(login_submit).attr("class", classes);
    $(login_submit).html("<i class='icon-refresh icon-spin'></i> 正在登陆...");
}

function submit_enable() {
    $(login_submit).attr("class", oldClasses);
    $(login_submit).removeClass("disabled");
    $(login_submit).html("登录");
}

function showNet_error() {
    var tips = "登录失败，请检查网络！";
    alertBox(tips, "warning")
}

function login_error() {
    var tips = "账号或密码错误，请重新输入！";
    alertBox(tips, "warning")
}

function login_success(result) {
    var tips = "登录成功，欢迎回来！";
    alertBox(tips, "success");
    //jquery 1.6 以后只能通过prop获得是否选中
    var remember = $("input[name=rememberMe]").prop("checked");
    if (remember) {
        //记住密码则保存24*7小时
        setCookie("token", result.extend.token, 24 * 7);
    } else {
        setCookie("token", result.extend.token, 0);
    }
    window.location.href = "http://gobang04.bzchao.com/userCenter.html";
}