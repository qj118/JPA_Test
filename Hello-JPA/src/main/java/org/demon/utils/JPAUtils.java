package org.demon.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理工厂对象，此后不再创建直接返回该工厂对象。
 */
public class JPAUtils {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
