# gobang04
五子棋社区，前后端分离

开发管理工具：IDEA, Maven, GitHub, Hbuilder
四大模块(独立部署)：SSO单点登录系统 + Netty即时通信服务 +
Web用户界面 + 客户端用户界面

前后端完全分离，SSM框架，CORS跨域访问，SSO单点登录，Bootstrap界面，RESTful构架风格，Netty即时通信。

网页端和客户端通过SSO单点登录系统进行身份验证，用户之间的通信采用Netty即使通信框架，且网页端可与客户端相互通信。

**本项目将在2018.09构架升级(已完成升级部署)
在线预览http://gobang04.bzchao.com


<img src="https://img-blog.csdn.net/20160406210559795" width="300" alt="我是缩小后的图"></img>


本次升级，更新授权认证方式(SSO)，实现各平台用户的通信(Netty+WebSocket+Okhttp)


>>>>![WEB端截图](https://github.com/bzsome/gobang04/blob/master/doc/browser-message.png?raw=true)
>>>>![客户端截图](https://github.com/bzsome/gobang04/blob/master/doc/client.png?raw=true)
