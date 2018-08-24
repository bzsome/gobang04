//全局Ajax错误处理
//全局Ajax错误处理
$(document).ajaxError(function (event, xhr, options, exc) {
    /* 错误信息处理 */
    /* 弹出jqXHR对象的信息 */
    console.log("Ajax request error!");
    console.log("status: " + xhr.status);
    console.log("statusText: " + xhr.statusText);
    console.log("readyState: " + xhr.readyState);
    console.log("responseText: " + xhr.responseText);
    console.log("exc" + exc);
});

//全局ajax发送前
$(document).ajaxSend(function (event, xhr, options) {
    console.log(">>>> url: " + options.url);
    console.log("request data: " + options.data);
});


$.ajaxSetup({
    // 将XHR对象的withCredentials设为true
    xhrFields: {
        //允许跨域带上cookie
        withCredentials: true
    },
    headers: {
        Authorization: getCookie("token")
    }
});