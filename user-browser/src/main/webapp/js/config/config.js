/**
 * 全局配置文件
 * @type {string}
 */

//服务器api接口地址
var oauth_path = "http://localhost:8080/oauth";
var netty_path = "gobang04.bzchao.com:9901/ws";
_settings = {
    gobangDown: "http://t.cn/RrXswvP",
    jdkDown: "http://down-www.newasp.net/pcdown/soft/yh/jre1.8x64.7z",
    api: {
        user: oauth_path + "/user/user",//用户操作接口(CRUD)
        login: oauth_path + "/user/login",//用户登录接口地址
        logout: oauth_path + "/user/logout",//注销登录接口地址
        register: oauth_path + "/user/register",//用户注册接口(CRUD)
        check: oauth_path + "/user/check",//信息后端检验
    },
    html: {
        index: "index.html",
        login: "login.html",
        userCenter: "userCenter.html",
        download: "download.html",
        gobangCenter:"gobang/index.html"
    }
}
