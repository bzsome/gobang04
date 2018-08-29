# gobang04
五子棋社区，前后端分离

        此版本基于上一版本：Gobang v03 https://github.com/bzsome/Gobang03
        
前后端完全分离，SSM框架，CORS跨域访问，SSO单点登录，Bootstrap界面，RESTful构架风格，Netty即时通信，Token口令授权。

### 开发管理工具：
  IDEA, Maven, GitHub, Hbuilder
  
### 四大模块(独立部署)：
  SSO单点登录系统 + Netty即时通信服务 + Web用户界面 + 客户端用户界面
#### 客户端模块在单独的一个项目中，[在线五子棋V02](https://github.com/bzsome/GobangClient02)

### 部署方式：
   SSO单点登录系统(server-oauth):打包成war，直接部署在Servlet容器中即可
   
          此模块依赖数据库，需要修改数据配置文件，dbconfig.properties
          
   Netty即时通信服务(server-netty):打包成jar，执行执行jar即可。
   
          websocket端口默认为9901，ClientChat端口默认为9902。如需修改端口，请修改NetttServer.java中相关代码。
          Netty登录基于单点登录系统，请在本模块Constants.java中修改单点登录系统API接口地址。
          
   Web用户界面(user-browser):可直接将webapp压缩，发布在静态WEB服务器中，或打包成war，发布在Servlet服务器中。
   
          WEB登录基于单点登录系统，请在js/config.js中修改单点登录系统API接口地址。
          
   客户端用户界面(user-client):打包成jar(maven)，执行执行jar即可。[客户端模块](https://github.com/bzsome/GobangClient02)
   
          客户端登录基于单点登录系统，请在本模块Constants.java中修改单点登录系统API接口地址。
   
   
网页端和客户端通过SSO单点登录系统进行身份验证，用户之间的通信采用Netty即使通信框架，且网页端可与客户端相互通信。
在线预览http://gobang04.bzchao.com

本次升级，更新授权认证方式(SSO)，实现各平台用户的通信(Netty+WebSocket+Okhttp)
### 项目构架

<img src="https://github.com/bzsome/gobang04/blob/master/doc/gobang构架图.png?raw=true" width="500"></img>
<img src="https://github.com/bzsome/gobang04/blob/master/doc/用户登陆时序图.png?raw=true" width="500"></img>
<img src="https://github.com/bzsome/gobang04/blob/master/doc/即时通讯时序图.png?raw=true" width="500"></img>

### 项目预览
<img src="https://github.com/bzsome/gobang04/blob/master/doc/browser-message.png?raw=true" width="500"></img>

<img src="https://github.com/bzsome/gobang04/blob/master/doc/client.png?raw=true" width="500"></img>
