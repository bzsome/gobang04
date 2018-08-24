/*加载用户数据*/

//用户信息数据
var userData = "";
//用户token口令
//利用动态元素标识，告知其它JS方法数据信息加载完毕。
var userTip = $("<div></div>").attr('id', "userdata_Tip");

$(function () {
    getUserData();
});

//用户退出登录
$(".loginOut").click(function () {
    loginOut();
});

//获得用户信息
function getUserData() {
    $.ajax({
        type: "get",
        url: _settings.api.user,
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //用户信息获取成功
                userData = result.extend.user;
            } else if (result.code == 403) { //用户信息获取成功
                userData = "";
            }
        },

        complete: function () {
            //ajax请求完成，添加Tip标签，并触发点击事件
            userTip.appendTo($("body"));
            //发送消息，告知数据用户加载完成
            userTip.click();
        }
    });
}

//显示用户信息再导航栏
function show_user_bar() {
    if (userData == null || userData.length == 0) {
        $("#navbar-user").hide();
        $("#navbar-login").show();
    } else {
        $("#navbar-user").find(".user-bar").text(userData.username);
        $("#navbar-user").show();
        $("#navbar-login").hide();
    }

}

//注销登录
function loginOut() {
    $.ajax({
        type: "get",
        url: _settings.api.logout,
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //注销成功
                alert("注销成功！");
                delCookie("token");
                window.location.href = _settings.html.login;
            } else {
                alert("注销登录失败！");
            }
        }
    });
}

function isLoginAndShow() {
    if (userData == null || userData.length == 0) {
        console.log("用户未登录！");
        showMyModal("提示！", "您未登录，无法进行此操作，请先登录。", function () {
            //跳转到登录页面
            window.location.href = _settings.html.login;
        });
        return false;
    }
    return true;
}

