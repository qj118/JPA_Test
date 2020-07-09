import org.demon.utils.JPAUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {

    /**
     * 查询全部客户
     *      sql: SELECT * FROM cst_customer
     *      jpql: from Customer
     */
    @Test
    public void findAll(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // jpql 语句作为参数创建 Query 对象
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);

        // 返回结果 getResultList 直接将查询结果封装为 list
        List list = query.getResultList();

        for(Object obj : list){
            System.out.println(obj);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    /**
     * 排序查询：倒序查询全部客户
     *      sql: SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql: from Customer order by custId desc
     */
    @Test
    public void findAllInverted(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // jpql 语句作为参数创建 Query 对象
        String jpql = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);

        // 返回结果
        List list = query.getResultList();

        for(Object obj : list){
            System.out.println(obj);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    /**
     * 统计函数：统计客户总数
     *      sql: SELECT count(*) FROM cst_customer
     *      jpql: select count(custId) from Customer
     */
    @Test
    public void count(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // jpql 语句作为参数创建 Query 对象
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);

        // 返回结果 getSingleResult 得到唯一结果
        Object result = query.getSingleResult();
        System.out.println(result);

        entityTransaction.commit();
        entityManager.close();
    }

    /**
     * 分页查询：
     *      sql: SELECT * FROM cst_customer LIMIT ?, ?
     *      jpql: from Customer
     */
    @Test
    public void page(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // jpql 语句作为参数创建 Query 对象
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);

        query.setFirstResult(0); // 对应 sql 语句中 limit 的第一个参数
        query.setMaxResults(2); // 对应 sql 语句中 limit 的第二个参数

        // 返回结果 getSingleResult 得到唯一结果
        List list = query.getResultList();
        for(Object obj : list){
            System.out.println(obj);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    /**
     * 条件查询：
     *      sql: SELECT * FROM cst_customer WHERE cust_name LIKE ?
     *      jpql: from Customer where custName like ?
     */
    @Test
    public void condition(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // jpql 语句作为参数创建 Query 对象
        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);

        /**
         * 参数 1 - 占位符索引，从 1 开始
         * 参数 2 - 占位符的值
         */
        query.setParameter(1, "%bili%");

        // 返回结果 getSingleResult 得到唯一结果
        List list = query.getResultList();
        for(Object obj : list){
            System.out.println(obj);
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
