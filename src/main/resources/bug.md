# bug 记录

## 注解报 null
- 问题：jwtConfig 在 AdminInterceptor 中使用依赖注入时，不能实例化
- 解决方法：未能直接解决，只能把 token 参数都写在了 jwtConfig 无参构造方法中，AdminInterceptor 中直接实例化


## 在place中加入image类后，创建bean失败，导致一开始的adminController就失败，报错太多，没有往下找原因

- 问题： 
    1. 忘记了报错信息时栈跟踪的，真正的报错原因往往在最后
    2. 没有 setter for Image， 原来是 mapper 的 association 的 property 属性值 image应该小

##    getAllPlaces 得到的image类没有其他属性加载
- 问题： 因为 mapper 的 select ... from 中忘记写了 image的属性

## 如何使用token使得每个学生账号只能操作自己的信息
- 尝试：通过token接触账号与目标操作账号是否一致

## 为何需要使用token对接口访问进行升级，使得访问需要登录状态
- 原因：假设你的api接口被别人知道了，假设没有无序登录就得使用api那别人就能随意通过操作api来修改你数据库的数据了
，那如何别人使用自己的账号来调用api修改别人的数据呢？那就需要服务器端进一步进行身份验证，可- 尝试：通过token接触账号与目标操作账号是否一致
来进行辨别，使得每个账号最多只能修改自己的数据。当然如何能从源头上不让别人知道自己的api就更好了。