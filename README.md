# summer-security-sso

一个简洁而易于使用的sso权限框架，方便项目快速上手

#### Spring Boot 父依赖

```xml
<!-- Spring Boot 启动父依赖 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.6</version>
    <relativePath/>
</parent>
```

#### knife4j swagger 升级版接口文档

```xml

<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>3.0.3</version>
</dependency>
```

#### hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本

```xml

<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.3</version>
</dependency>
```