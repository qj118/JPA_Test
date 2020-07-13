package org.demon.dao;

import org.demon.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *     封装了基本 CRUD 操作
 * JpaSpecificationExecutor<操作的实体类类型>
 *     封装了复杂查询（分页）
 */
public interface CustomerDAO extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 根据客户名查询客户
     * 使用 jpql 形式查询： from Customer where custName = ?
     * @param custName
     * @return
     */
    @Query(value = "from Customer where custName = ?1")
    Customer findByName(String custName);

    /**
     * 根据客户名称和客户 id 查询客户
     * jpql: from Customer where custName = ? and custId = ?
     *
     * 对于多个占位符参数，
     *      默认的情况下，占位符的位置需要和方法参数中的位置保持一致；
     *      ?+索引，指定占位符的取值来源。
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    Customer findByNameAndId(String name, Long id);

    /**
     * 更新客户名称
     * jpql: update Customer set custName = ? where custId = ?
     * @Modifying 注解表示当前是更新操作
     */
    @Query(value = "update Customer set custName = ?1 where custId = ?2")
    @Modifying
    void updateCustName(String custName, Long custId);

    /**
     * 使用 sql 语句查询：查询全部客户
     * sql: select * from cst_customer
     * Query 配置 sql 语句
     *      value: sql 语句
     *      nativeQuery: 查询方式
     *          true: sql 查询
     *          false: jpql 查询
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    List<Object[]> findAllBySQL();

    /**
     * 条件查询：根据客户名称模糊查询st
     * sql: select * from cst_customer where cust_name like ?
     */
    @Query(value= "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    List<Object[]> queryByName(String name);

    /**
     * 方法命名规则查询：按客户名称查询
     * findBy + 属性名（首字母大写）
     */

    Customer findByCustName(String custName);

    /**
     * 方法命名规则查询：模糊查询
     * findBy + 属性名称 + Like
     */
    Customer findByCustNameLike(String custName);

    /**
     * 方法命名规则查询：多条件查询
     * findBy + 属性名称 + [查询方式] + 连接运算 + 属性名 + [查询方式]
     */
    Customer findByCustNameLikeAndCustIndustry(String custName, String custIndustry);
}
