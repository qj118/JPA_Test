## 目录简介

### Hello-JPA

- 两个测试类
    1. JpaTest JPA规范下的增删改查测试
    2. JpqlTest 基于 JPQL 的复杂查询测试
- 一个实体类
- 一个 JPA 工具类

### Hello-Spring-Date-JPA

目前存在问题：org.springframework.transaction.CannotCreateTransactionException: Could not open JPA EntityManager for transaction; nested exception is javax.persistence.PersistenceException: org.hibernate.exception.GenericJDBCException: Unable to acquire JDBC Connection