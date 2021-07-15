bandwidth 一个代理工具
================================

基于netty开发，支持socks5，http(包括https)两种代理方式；其中socks5只支持IPV4 TCP CONNECT模式。
客户端服务器间采用多路复用模式。需手动配置DNS服务器IP列表。

适用于按流量计费虚拟机，不适用于低带宽虚拟机。


使用
-------------------------
使用之前你需要先安装JAVA，推荐JDK8，别的版本没测试过。

程序优先读取`config`目录下的`config.json`文件，如果没读到，读取用户根目录下的`config.json`文件。

配置文件模板：
```
{
  "localSocksPort": "20070",
  "localHttpPort": "20080",
  "serverHost": "--服务器公网IP--",
  "serverPort": "--服务器公网端口--",
  "secretKey": "--服务器客户端握手token--",
  "serverBindIp": "--服务器绑定的内网IP，可以直接使用0.0.0.0--",
  "serverBindPort": "--服务器内网端口--",
  "dnsList": "--dns ip 列表，‘,’ 分割--"
}
```

目录结构
-------------------------
编译之后的结果是一个名字`bandwidth-xx-tool.tar.gz`样式的压缩包，里面有三个文件夹

`bin` 程序启动文件，windows客户端启动程序为`download.bat`文件，linux服务器端启动程序为`upload`文件

`config` 配置文件目录，配置文件名称为`config.json`，可放在该目录也可放在`user.home`目录下

`repo` 目录存的是jar文件，可忽略

windows上双击bat文件即可启动，linux上可以通过nohup命令后台启动。


**请在国家法律规定下合法合规的使用该软件**
