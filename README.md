# 说明

#### 本次使用到的技术栈

* 前端

  * Vue
  * ElementUI
  * Axios

* 后端

  * Java
  * spring boot
    * mybatis
  * spring cloud
    * Eureka注册中心
    * Zuul网关
  * Mysql数据库

#### 后端模块划分

* eureka注册中心，负责微服务的治理
* gateway网关，负责API服务的统一管理
* provider服务提供者，组件服务提供者，负责组件的增删改查
* consumer服务消费者，使用RestTemplate简单调用服务提供者符合REST规范的服务，意义仅在于展示微服务的意义，不符合REST规范的服务依然直接访问provider，例如批量删除，批量更新这两个接口



  

  

  