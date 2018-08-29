# gobang04 五子棋社区

在线预览  http://gobang04.bzchao.com

此版本基于上一版本：[Gobang v03](https://github.com/bzsome/Gobang03)

此软件完全独立设计开发，本人拥有所有版权。

### 关键技术
> * 前后端完全分离，SSM框架，CORS跨域访问，SSO单点登录，Bootstrap界面，RESTful构架风格，Netty即时通信，Token口令授权，Web端与客户端通信。
### 构建说明
> * 项目采用IDEA集成开发工具，Maven项目构建工具，使用Git版本控制，JUnit单元测试工具，Log4j日志记录等。Gson序列化工具，Okhttp网络请求工具。
### 项目简介
> * 用户可在客户端和Web端，进行游戏对战，以及即时聊天。且客户端与Web端之间能够即时通信。
> * 用户端能够保存用户信息，以便下次自动登录。用户如需注册需打开Web端的注册页面。
## 项目模块
**1. SSO单点登录系统(server-oauth)：**
- 采用SSM框架，MyBatis逆向工程，RESTful构架风格生成通用API接口。
- 为其他系统模块提供身份验证，使用JWT生成token口令授权。

**2. Netty即时通信服务(server-netty)：**
- 处理用户之间的即时消息,包括聊天信息，下棋对战信息。且客户端和WEB可相互通信。
- 同时会与单点登录系统进行交互，判断用户时候有相应权限。

**3. Web用户界面(user-browser)：**
- 纯静态页面：HTML，CSS，jQuery, Ajax, BootStrap。
- 用户可以在WEB端进行登录注册，修改资料。与其他在线用户进行五子棋游戏，在线聊天等。

**4.客户端用户界面(user-client)：**
- 通过Okhttp3与登录系统交互，使用Netty进行即时通信。
- 用户可以在客户端端进行登录，启动软件能够自动登录。与其他在线用户进行五子棋游戏，在线聊天等。
- 客户端模块在单独的一个项目中，[在线五子棋客户端V02](https://github.com/bzsome/GobangClient02)

### 项目构架图
<img src="https://github.com/bzsome/gobang04/blob/master/doc/gobang构架图.png?raw=true" width="500"></img>
### 用户登陆时序图
<img src="https://github.com/bzsome/gobang04/blob/master/doc/用户登陆时序图.png?raw=true" width="500"></img>
### 即时通讯时序图
<img src="https://github.com/bzsome/gobang04/blob/master/doc/即时通讯时序图.png?raw=true" width="500"></img>
### 项目预览
<img src="https://github.com/bzsome/gobang04/blob/master/doc/browser-message.png?raw=true" width="500"></img>
<img src="https://github.com/bzsome/gobang04/blob/master/doc/client.png?raw=true" width="500"></img>
