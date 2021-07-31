# bug 记录

## 注解报 null
- 问题：jwtConfig 在 AdminInterceptor 中使用依赖注入时，不能实例化
- 解决方法：未能直接解决，只能把 token 参数都写在了 jwtConfig 无参构造方法中，AdminInterceptor 中直接实例化