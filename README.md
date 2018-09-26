## About
Fork from https://github.com/longjiazuo/springMvc4.x-project  
基于此代码，修改了一点小问题，并加了一些自己的demo


## 增加模块
#### ■ springMvc4.x-argument

和参数相关，涉及2个要点：

- 参数注入
- 对象参数校验
- 普通参数校验

##### 参数注入
应用场景:
- 从cookie中提取User对象，并塞到action/RequestMapping 的方法中

实现要点：
- 增加实现类，实现HandlerMethodArgumentResolver接口
- 在MyMvcConfig中覆写addArgumentResolvers，添加上面自定义的实现类


##### 对象参数校验
实现要点：
- 添加 hibernate-validator 依赖
- 参数form前需要加@Validated注解 [即使类头上加了，这里也需要加]
- form对象中的属性参数上加@NotNull之类的注解


##### 普通参数校验
实现要点：
- 添加 hibernate-validator 依赖
- 在MyMvcConfig中，new一个MethodValidationPostProcessor交给Spring容器管理[对象参数校验无需此对象]
- 在Controller的类头上需要加 @Validated 注解 [一定是加载类头上，加载参数前无效]
