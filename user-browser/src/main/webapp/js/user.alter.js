var user_div = "#user-info";//用户信息显示表单
$(function () {
    //用户提交事件处理
    $(user_div).find("#submit").click(function (e) {
        e.preventDefault();
        updateUser();
    });
    alertBox("修改信息请输入原密码！<br/>留空则不修改原信息。", "success");
});

//接收用户信息加载完成事件消息
$(document).on("click", "#userdata_Tip", function () {
    show_user_bar();
    show_user_alter();
});

//提交更新信息请求
function updateUser() {
    $.ajax({
        type: "PUT",
        url: _settings.api.user,
        data: $(user_div).serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //修改成功
                userData = result.extend.user;
                alertBox("保存成功", "success");
                showMyModal("修改成功", "您的信息已修改成功！", null);
            } else {
                alertBox("服务拒绝保存!<br/>修改信息请输入原密码！", "warning");
            }
        }
    });
}

function show_user_alter() {
    if (!isLoginAndShow()) {
        return;
    }
    $(user_div).find("#username").val(userData.username);
    $(user_div).find("#phoneNum").val(userData.phone)
    $(user_div).find("#email").val(userData.email);
    $(user_div).find("#sign").val(userData.sign);
}