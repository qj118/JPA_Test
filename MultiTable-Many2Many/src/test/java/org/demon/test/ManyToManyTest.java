package org.demon.test;

import org.demon.dao.RoleDao;
import org.demon.dao.UserDao;
import org.demon.domain.Role;
import org.demon.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void add(){
        User user = new User();
        user.setUserName("Demon");

        Role role = new Role();
        role.setRoleName("程序员");

        /**
         * 多对多关系中，必须有一方放弃外键的维护权，否则抛出异常-主键冲突
         * 建议：被动的一方放弃维护权
         */
        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void cascadeAdd(){
        User user = new User();
        user.setUserName("Demon");

        Role role = new Role();
        role.setRoleName("程序员");


        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void cascadeDelete(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }
}
