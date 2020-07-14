package org.demon.test;

import org.demon.dao.CustomerDao;
import org.demon.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件，查询单个对象
     */
    @Test
    public void findOneByCustName(){
        /**
         * 自定义查询条件
         *   1. 实现 Specification 接口（提供泛型 - 查询的对象类型）
         *   2. 实现 toPredicate 方法（构造查询条件）
         *   3. 借助方法参数：
         *      1) root 获取需要查询的对象；
         *      2) criteriaBuilder 构造查询条件，内部封装了很多的查询条件。
         */
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1. 获取相关属性
                Path<Object> custName = root.get("custName");

                //2. 构造查询条件
                Predicate predicate = criteriaBuilder.equal(custName,"华研国际");// 精确匹配
                return predicate;
            }
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     *      根据客户名和客户所属行业查询
     */
    @Test
    public void findOne(){
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 1. 获取相关属性
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                //2. 构造客户名的精准匹配查询
                Predicate pre1 = criteriaBuilder.equal(custName, "哇唧唧哇nsl");
                //3. 构造客户所属行业的精准匹配查询
                Predicate pre2 = criteriaBuilder.equal(custIndustry, "娱乐文化");
                //4. 将多个查询条件组合到一起
                Predicate predicate = criteriaBuilder.and(pre1, pre2);
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 客户名称模糊匹配，返回客户列表
     */
    @Test
    public void findAll(){
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                /**
                 * gt, lt, ge, le ,like 得到 Path 对象后，需要根据 Path 对象获取比较的参数类型，再进行比较。
                 *      指定参数类型， path.as(类型的字节码对象)。
                 */
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "%哇唧唧哇%");
                return predicate;
            }
        };

        List<Customer> list = customerDao.findAll(spec);
        for(Customer customer : list){
            System.out.println(customer);
        }
        // 添加排序
        Sort sort  = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> listDesc = customerDao.findAll(spec, sort);
        for(Customer customer : listDesc){
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     *      findAll(Specification, Pageable) 带条件分页
     *      findAll(Pageable) 没有条件的分页
     */
    @Test
    public void page(){
        Specification<Customer> spec = null;
        /**
         * 创建 PageRequest 的过程中，需要调用其的构造方法，并传入两个参数：
         *      1. 当前查询的页数（从 0 开始）；
         *      2. 每页查询的数量。
         */
        Pageable pageable = new PageRequest(0, 2);
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println(page.getContent()); // 得到数据集合列表
        System.out.println(page.getTotalElements()); // 得到总条数
        System.out.println(page.getTotalPages());// 得到总页数
    }

}
