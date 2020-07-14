package org.demon.test;

import org.demon.dao.CustomerDao;
import org.demon.dao.LinkmanDao;
import org.demon.domain.Customer;
import org.demon.domain.Linkman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkmanDao linkmanDao;

    @Test
    @Transactional
    @Rollback(false)
    public void add(){
        Customer customer = new Customer();
        customer.setCustName("华研国际");
        customer.setCustIndustry("娱乐文化");

        Linkman linkman = new Linkman();
        linkman.setLkmName("Ella");

        /**
         * 配置客户到联系人的关系（一对多）
         *      从客户的角度上，发送两条 insert 语句，发送一条更新语句更新联系人表的外键。
         */
        customer.getLinkMans().add(linkman);

        customerDao.save(customer);
        linkmanDao.save(linkman);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void add2(){
        Customer customer = new Customer();
        customer.setCustName("华研国际");
        customer.setCustIndustry("娱乐文化");

        Linkman linkman = new Linkman();
        linkman.setLkmName("Ella");

        /**
         * 配置联系人到客户的关系（多对一）
         *     从联系人的角度上，只发送两条 insert 语句。
         */
        linkman.setCustomer(customer);

        customerDao.save(customer);
        linkmanDao.save(linkman);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void add3(){
        Customer customer = new Customer();
        customer.setCustName("华研国际");
        customer.setCustIndustry("娱乐文化");

        Linkman linkman = new Linkman();
        linkman.setLkmName("Ella");

        /**
         * 配置双向联系
         *    由于配置多对一的联系时已经保存了外键，所以在执行一对多配置时，会多出一条 update 语句。
         *    可以让一的一方放弃外键的维护权。
         */
        linkman.setCustomer(customer);
        customer.getLinkmen().add(linkman);

        customerDao.save(customer);
        linkmanDao.save(linkman);
    }

    /**
     * 级联添加：保存一个客户的同事，保存客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void cascadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("华研国际");
        customer.setCustIndustry("娱乐文化");

        Linkman linkman = new Linkman();
        linkman.setLkmName("Hebe");

        linkman.setCustomer(customer);
        customer.getLinkmen().add(linkman);

        customerDao.save(customer); // 需要在实体类上配置 cascade 属性
    }

    /**
     * 级联删除
     */
    @Test
    @Transactional
    @Rollback(false)
    public void cascadeDelete(){
        Customer customer = customerDao.findOne(1l);
        customerDao.delete(customer);
    }
}
