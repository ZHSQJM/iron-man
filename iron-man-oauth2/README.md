
oAuth协议为用户资源的授权提供了一个安全的，开发而又建议的标准，与以往的授权方法不同之处是oAuth的授权不会使用地

- 获取授权码
```java
http://localhost:7800/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.zhouhuasheng.top
```
- 使用授权码获取accesToken
```java
http://client:secret@localhost:7800/oauth/token POST
grant_type:authorization_code
code
redirect_uri:http://www.zhouhuasheng.top
```
http://client:secret@localhost:7800/oauth/token?response_type=authorization_code&code=12346&redirect_uri=http://www.zhouhuasheng.top&scope=all

-OAuth四种授权方式
- **授权码模式**(authorization code) 用在客户端与服务端应用之间授权 
- 简化模式(implicit) 用在移动APP或者web app(这些app实在用户的设备上，如在手机上调起微信来进行认证授权)
- **密码模式** (resource owner password credentials) 应用直接都是受信任的(都是由一家公司开发的)
- 客户端模式(client credentials) 用在应用API访问

#### oauth2 角色划分
- Resource Server 被授权访问的资源
- Authotization Server Oauth2认证授权中心
- Resource Owner:用户
- Client: 使用API的客户端（Web/APP）


第三方应用程序: 
HTTP服务提供商:笔记服务/网盘服务/相册服务
资源所有者:自己的笔记/网盘/相册
用户代理:指代浏览器/APP
认证(授权)服务器:访问服务必须先去认证授权
资源服务器:笔记服务器


oauth2.0是什么
oauth2.0是协议 标准 JPA是一种数据持久化的协议


