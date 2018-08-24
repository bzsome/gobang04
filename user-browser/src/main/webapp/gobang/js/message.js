var PATH = netty_path;
var TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzQ0OTg4OTcsInVzZXJuYW1lIjoiY2hhb2EifQ.aLAmGsIgSib0Cj1sgVt-OZlG-VMwGvcqT97t8y-_IfE";
var message = {
    send_user: "system",
    receive_user: "",
    msg_text: ""
};

var websocket = null;

function openSocket() {
    if (window['WebSocket']) {
        websocket = new WebSocket("ws://" + PATH + "?token=" + TOKEN);
    } else {
        console.log("new SockJS");
        websocket = new new SockJS(PATH + '/websocket/socketjs');
    }
    websocket.onopen = function (event) {
        console.log('open', event);
    }

    websocket.onmessage = function (event) {
        console.log("onmessage");
        console.log(event.data);
        try {
            var jsObj = JSON.parse(event.data);
            var msg2 = jsObj.send_user + ":" + jsObj.msg_text;
            showMessage(msg2);
        } catch (e) {
            showMessage(event.data);
        }
    }
    websocket.onclose = function () {
        console.log("连接关闭");
        showMessage("连接关闭");
    }
    websocket.error = function () {
        console.log("连接错误");
        showMessage("连接错误");
    }

}

function showMessage(msg) {
    $(' #message_div > ul').append('<li>' + msg + '</li>');
}

//发送消息
function send() {
    message.receive_user = $("#username").val();
    message.msg_text = $("#msg_txt").val();
    console.log("send");
    console.log(message);
    websocket.send(JSON.stringify(message));
}

function loadInfo() {
    TOKEN = getCookie("token");
}


$(function () {
    loadInfo();
    openSocket();
})