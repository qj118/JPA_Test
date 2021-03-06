package org.demon.test;

import org.demon.dao.CustomerDAO;
import org.demon.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

    /**
     * 根据 id 查询
     */
    @Test
    public void findOne(){
        Customer customer = customerDAO.findOne(1l);
        System.out.println(customer);
    }

    /**
     * save: 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有 id 主键属性，则新增数据；
     *      存在 id 主键属性，根据 id 查询数据，更新数据；
     */
    @Test
    public void save(){
        Customer customer = new Customer();
        customer.setCustName("华研国际");
        customer.setCustIndustry("娱乐文化");
        customerDAO.save(customer);
    }

    @Test
    public void update(){
        Customer customer = new Customer();
        customer.setCustId(2l);
        customer.setCustIndustry("娱乐");
        customerDAO.save(customer);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        customerDAO.delete(2l);
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        List<Customer> list = customerDAO.findAll();
        for(Customer customer: list){
            System.out.println(customer);
        }
    }

    /**
     * 统计从查询 count
     */
    @Test
    public void count(){
        long count = customerDAO.count();
        System.out.println(count);
    }

    /**
     * 判断客户是否存在
     */
    @Test
    public void exists(){
        boolean exists = customerDAO.exists(5l);
        System.out.println("id为5的客户是否存在？" + exists);
    }

    /**
     * 根据 id 查询客户
     * findOne
     *      em.find() 立即加载
     * getOne
     *      em.getReference() 延迟加载
     */
    @Test
    @Transactional
    public void getOne(){
        Customer customer = customerDAO.getOne(6l);
        System.out.println(customer);
    }

}
