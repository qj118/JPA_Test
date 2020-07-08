import org.demon.domain.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    @Test
    public void test(){

        // 1. 创建实体管理类工厂，参数来自于配置文件
        EntityManagerFactory  factory = Persistence.createEntityManagerFactory("myJpa");
        // 2. 创建实体管理类
        EntityManager em = factory.createEntityManager();
        // 3. 获取事务对象
        EntityTransaction et = em.getTransaction();
        // 4. 开启事务
        et.begin();
        Customer c = new Customer();
        c.setCustName("bilibili");
        // 5. 保存操作
        em.persist(c);
        // 6. 提交事务
        et.commit();
        // 7. 释放资源
        em.close();
        factory.close();
    }
}
