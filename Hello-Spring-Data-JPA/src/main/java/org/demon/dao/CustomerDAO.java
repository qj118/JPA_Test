package org.demon.dao;

import org.demon.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *     封装了基本 CRUD 操作
 * JpaSpecificationExecutor<操作的实体类类型>
 *     封装了复杂查询（分页）
 */
public interface CustomerDAO extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
