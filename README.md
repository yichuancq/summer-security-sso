# summer-security-sso

一个简洁而易于使用的sso权限框架，方便项目快速上手。jwt权限认证，redis缓存，nacos注册中心。

#### 系统模块

| 模块名称       | 功能   | 备注             |
|:-----------------------|:---------------|:---------------|
| summer-gateway-server  | 网关服务           | 网关实现权限认证，流量监控等 |
| summer-oath-server     | 后端权限服务         | 后端权限服务         |
| summer-common          | 公共类            | 公共类            |
| summer-admin-server    | spring admin管理 | 方便运行参数查看       |
| summer-resource-server | 资源服务           | 文件上传、下载        |
| summer-seller-server   | 销售服务           | 功能子模块          |
| summer-bill-server     | 订单服务            | 功能子模块          |
| summer-log             | 日志切面            | 自定义注解日志记录      |

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
> https://doc.xiaominfo.com/
```xml

<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>3.0.3</version>
</dependency>
```

#### hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本

> https://gitee.com/dromara/hutool

```xml

<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.3</version>
</dependency>
```

#### post 请求示例

```text
curl -X POST -H  "Accept:*/*" -H  "Content-Type:application/json" -d "{\"id\":\"12\",\"name\":\"2342324\"}" "http://localhost:9001/api/seller/save"
```

#### 请求响应

```json
{
  "code": 200,
  "message": "请求成功！",
  "data": {
    "id": "12",
    "name": "2342324"
  },
  "resultMap": {}
}
```

#### knife4j api url

> http://localhost:9001/doc.html#/home

#### 自定义日志注解，@Aspect 自定义切面

```java

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String value() default "";

}
```

#### controller使用日志

```java
/**
 * save
 * @param requestParams
 * @return
 */
@SystemLog(value = "save")
@PostMapping(value = "save")
public ResultData<?> save(@RequestBody RequestParams requestParams){
        log.info("test->requestParams:{}",requestParams);
        return new ResultData<>(ResultCode.SUCCESS,requestParams);
        }
```