/**
 * 用户注册相关
 * @type {RegExp}
 */

var regUsername = /^[a-zA-Z_][a-zA-Z0-9_]{4,19}$/;
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;
var regPasswordAlpha = /[a-zA-Z]/;
var regPasswordNum = /[0-9]/;
var check = [false, false, false];

//提交注册
function regAjax() {
    var reg_from=$("#regFrom");
    $.ajax({
        type: "POST",
        url: _settings.api.register,
        data: reg_from.serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //注册成功
                $('#myModal').modal('show');
                showMyModal("注册成功！", "你已注册成功，注册信息已发送道破你的邮箱。", function () {
                    window.location.href = _settings.html.userCenter;
                });
            } else {
                showMyModal("注册失败！", "拒绝注册，服务器拒绝您的注册请求，请重试！");
            }
        },
        error: function () {
            showMyModal("网络检查！", "网络错误，请检查网络后重试！");
        }
    });
}

// 用户名匹配
//$('.container').find('#username').change(usernameVar).blur(usernameVar);

$('.container').find('#username').change(usernameVar);
function usernameVar() {
    if (regUsername.test($(this).val())) {
        isHaveUsername($(this).val());
    } else if ($(this).val().length < 5) {
        fail($(this), 0, '用户名太短，不能少于5个字符');
    } else {
        fail($(this), 0, '用户名只能为英文数字和下划线,且不能以数字开头')
    }
}

/**
 *验证用户名是否存在
 * @param account
 */
function isHaveUsername(username) {
    $.ajax({
        type: "post",
        url: _settings.api.check + "/username",
        data: "username=" + username,
        xhrFields: {
            //允许跨域带上cookie
            withCredentials: true
        },
        success: function (result) {
            console.log(result);
            if (result.code == 200) { //账号未注册
                success("#username", 0);
            } else {
                fail("#username", 0, result.extend.msg)
            }
        },
        error: function () {
            fail("#username", 0, '网络错误，无法验证！')
        }
    });
}

// 密码匹配
// 匹配字母、数字、特殊字符至少两种的函数
function atLeastTwo(password) {
    var a = regPasswordSpecial.test(password) ? 1 : 0;
    var b = regPasswordAlpha.test(password) ? 1 : 0;
    var c = regPasswordNum.test(password) ? 1 : 0;
    return a + b + c;
}


$('.container').find('#password').change(function () {
    if ($(this).val().length < 6) {
        fail($(this), 1, '密码太短，不能少于6个字符');
    } else {
        if (atLeastTwo($(this).val()) < 2) {
            fail($(this), 1, '密码中至少包含字母、数字、特殊字符的两种')
        } else {
            success($(this), 1);
        }
    }
});

// 再次输入密码校验
$('.container').find('#passwordConfirm').change(function () {
    var password = $('.container').find('#password').val();
    if ($(this).val() == password) {
        success($(this), 2);
    } else {
        fail('#passwordConfirm', 2, '两次输入的密码不一致');
    }
});


$('#submit').click(function (e) {
    e.preventDefault();
    if (!check.every(function (value) {
        if (value == true) {
            regAjax();
        }
    })) {
        for (key in check) {
            if (!check[key]) {
                $('.container').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    }
});


//校验成功函数
function success(ele, counter) {
    $(ele).parent().parent().removeClass('has-error').addClass('has-success');
    $(ele).parent().siblings('.tips').hide();
    $(ele).parent().siblings('.glyphicon-ok').show();
    $(ele).parent().siblings('.glyphicon-remove').hide();
    check[counter] = true;
}

// 校验失败函数
function fail(ele, counter, msg) {
    $(ele).parent().parent().removeClass('has-success').addClass('has-error');
    $(ele).parent().siblings('.glyphicon-remove').show();
    $(ele).parent().siblings('.glyphicon-ok').hide();
    $(ele).parent().siblings('.tips').text(msg).show();
    check[counter] = false;
}
