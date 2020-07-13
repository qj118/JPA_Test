package org.demon.test;

import org.demon.dao.CustomerDAO;
import org.demon.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDAO customerDAO;

    @Test
    public void findByName(){
        Customer customer = customerDAO.findByName("哇唧唧哇");
        if(customer != null){
            System.out.println(customer);
        }else{
            System.out.println("Not exists.");
        }
    }

    @Test
    public void findByNameAndId(){
        Customer customer = customerDAO.findByNameAndId("哇唧唧哇", 3l);
        if(customer != null){
            System.out.println(customer);
        }else{
            System.out.println("Not exists.");
        }
    }

    /**
     * spring data jpa 中使用 jpql 完成 update/delete 操作
     *      *需要手动添加事务的支持
     *      *默认会执行结束之后，回滚事务，需要手动将回滚设为 false
     */
    @Test
    @Transactional // 添加事务支持
    @Rollback(value = false)
    public void updateCustName(){
        customerDAO.updateCustName("哇唧唧哇nsl", 4l);
    }

    @Test
    public void findAllBySQL(){
        List<Object[]> list = customerDAO.findAllBySQL();
        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void queryByName(){
        List<Object[]> list = customerDAO.queryByName("%nsl%");
        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void findByCustName(){
        Customer customer = customerDAO.findByCustName("华研国际");
        System.out.println(customer);
    }

    @Test
    public void findByCustNameLike(){
        Customer customer = customerDAO.findByCustNameLike("%华研%");
        System.out.println(customer);
    }

    @Test
    public void findByCustNameLikeAndCustIndustry(){
        Customer customer = customerDAO.findByCustNameLikeAndCustIndustry("%华研%", "娱乐文化");
        System.out.println(customer);
    }

}
