package org.demon.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "age")
    private Integer age;

    /**
     * 配置用户到角色的多对多的关系
     *      1. @ManyToMany
     *      2. @JoinTable 配置中间表
     *          name 中间表名称
     *          joinColumns 当前对象在中间表中的外键，
     *              `@JoinColumn`的数组
     *          inverseJoinColumns 对方表在中间表中的外键
     */
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
               // 当前对象在中间表中的外键
               joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
               // 对方表在中间表中的外键
               inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}
               )
    private Set<Role> roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
