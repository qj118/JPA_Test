package org.demon.test;

import org.demon.dao.CustomerDao;
import org.demon.dao.LinkmanDao;
import org.demon.domain.Customer;
import org.demon.domain.Linkman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkmanDao linkmanDao;

    /**
     * 从客户对象导航查询他的所有的联系人
     * 默认: 延迟加载，即用到从表，才去查询从表。
     */
    @Test
    @Transactional
    public void query(){
        Customer customer = customerDao.getOne(1l);
        Set<Linkman> linkmen = customer.getLinkmen();
        for(Linkman linkman : linkmen){
            System.out.println(linkman);
        }
    }

    /**
     * 从联系人对象导航查询他的客户
     * 默认：立即加载，即用户和联系人一起查出。
     * 查询主体是谁，就在谁的主题类设置 fetch 属性。
     */
    @Test
    @Transactional
    public void query2(){
        Linkman linkman = linkmanDao.findOne(2l);
        Customer customer = linkman.getCustomer();
        System.out.println(customer);
    }
}
