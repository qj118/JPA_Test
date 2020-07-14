## 目录简介

### Hello-JPA

- 两个测试类
    1. JpaTest JPA规范下的增删改查测试
    2. JpqlTest 基于 JPQL 的复杂查询测试
- 一个实体类
- 一个 JPA 工具类

### Hello-Spring-Date-JPA

- 如果出现问题：A ResourcePool could not acquire a resource from its primary factory or source.
需要在dataSource配置中加上是时区配置`&amp;serverTimezone=Asia/Shanghai`, mysql5.7 + 需要加上该配置。
- resources/applicationContext.xml Spring 配置文件；
- domain 实体类包；
- dao 接口类包；
具体的查询方法无需提供，只需提供接口方法，其他的由框架自动生成。
- 测试类中测试方法与被测试的接口同名，以便查找。

### Specification-Test

对 JpaSpecificationExecutor 接口中的使用及测试。
1. 定制自己的 Specification;
2. 以 Specification 对象作为参数，调用接口。

### MultiTable-One2Many

多表操作之一对多的相关知识点。
1. @OneToMany 配置实体之间的关系；
2. @JoinColumn 配置外键;
3. “一” 中包含 “多” 的集合属性，“多” 中包含 “一” 的实体属性。

### MultiTable-Many2Many

多表操作之多对多的相关知识点。
1. @ManyToMany
2. @JoinTable 配置中间表
3. 注意被动方需要放弃外键维护权