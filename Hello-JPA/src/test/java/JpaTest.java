import org.demon.domain.Customer;
import org.demon.utils.JPAUtils;
import org.junit.Test;

import javax.persistence.*;

public class JpaTest {

    /**
     * 使用 JPA 规范操作流程
     */
    @Test
    public void testSave1(){

        // 1. 创建实体管理类工厂，参数来自于配置文件
        EntityManagerFactory  factory = Persistence.createEntityManagerFactory("myJpa");
        // 2. 创建实体管理类
        EntityManager em = factory.createEntityManager();
        // 3. 获取事务对象
        EntityTransaction et = em.getTransaction();
        // 4. 开启事务
        et.begin();
        Customer c = new Customer();
        c.setCustName("乐华娱乐");
        c.setCustIndustry("娱乐文化");
        // 5. 保存操作
        em.persist(c);
        // 6. 提交事务
        et.commit();
        // 7. 释放资源
        em.close();
        factory.close();
    }

    /**
     * 新增客户
     * 1. 新建客户对象
     * 2. 保存客户 - persist
     */
    @Test
    public void save(){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Customer customer = new Customer();
        customer.setCustName("腾讯视频");
        em.persist(customer);
        et.commit();
        em.close();
    }

    /**
     * 通过 id 查询数据 - find
     * 使用 find 方法查询：
     *  1. 获取的对象就是当前客户对象本身
     *  2. 在调用 find 方法的时候，就会发送 sql 语句查询数据库
     *
     * 立即加载
     */
    @Test
    public void findCustomerById(){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();

        /**
         * find - 根据主键查询数据
         *      参数1 返回的实体类型字节码
         *      参数2 主键值
         */
        Customer customer = em.find(Customer.class, 1l);
        System.out.println(customer);

        et.commit();
        em.close();
    }

    /**
     * 通过 id 查询数据 - getReference
     * 使用 getReference 方法查询：
     *  1. 获取的对象是一个动态代理对象；
     *  2. 当调用查询结果对象的时候，才会发送查询的 sql 语句；
     *
     * 延迟加载（懒加载）
     */
    @Test
    public void getCustomerById(){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        Customer customer = em.getReference(Customer.class, 1l);
        System.out.println(customer);

        et.commit();
        em.close();
    }

    /**
     * 删除客户操作
     * 1. 查找客户 - getReference
     * 2. 删除客户 - remove
     */
    @Test
    public void remove(){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        Customer customer = em.getReference(Customer.class, 1l);
        em.remove(customer);

        et.commit();
        em.close();
    }

    /**
     * 更新客户操作
     * 1. 查找客户 - getReference
     * 2. 更新客户 - merge
     */
    @Test
    public void update(){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        Customer customer = em.getReference(Customer.class, 1l);
        customer.setCustIndustry("文化");
        em.merge(customer);

        et.commit();
        em.close();
    }

}
