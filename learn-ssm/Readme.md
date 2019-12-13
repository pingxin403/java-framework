SSM即Spring SpringMVC Mybatis,虽然现在使用SpringBoot进行后端开发方便快捷了许多，但是对于我们Java程序猿来说，维护好旧项目也是一种能力。

一个SSM项目需要及基本依赖。

1. spring相关：spring-core,spring-beans,spring-context,spring-jdbc,spring-tx,spring-web,spring-webmvc,spring-test。每个依赖在上面均有相关注释

2. mybatis相关：mybatis,mybatis-spring。

3. mysql相关：mysql-connector-java(mysq连接驱动),c3p0(数据库连接池，可以更换为其他连接池)

4. 其他还有单元测试junit、日志框架logback-classic、Servlet服务相关的javax.servlet-api、json数据解析的jackson-databind。这里需要着重提醒一下，javax.servlet-api的缺少会导致项目看似启动成功，而访问项目失败的问题。除了以上提及的这些jar包之外，我们应该根据业务需求引入其他jar包，比如文件上传、redis客户端等等。