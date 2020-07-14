package org.demon.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // 声明实体类
@Table(name = "cst_customer") // 关联数据表
public class Customer {

    @Id // 声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 配置主键的生成策略
    @Column(name = "cust_id") // 关联数据表字段
    private Long custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_phone")
    private String custPhone;

    /** 配置客户和联系人之间的关系
     *
     *  使用注解的形式配置多表关系
     *      1. 声明关系 @OneToMany 配置一对多关系
     *      2. 配置外键 @JoinColumn
     *          name: 外键字段名称
     *          referenceColumnName: 参照的主表的主键字段名称
     *
     *  在客户实体类上，即一的一方添加了外键配置，所以对于客户而言，也具备了维护外键的作用。
     */
    @OneToMany(targetEntity = Linkman.class)
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    private Set<Linkman> linkMans = new HashSet<Linkman>();


    public Set<Linkman> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<Linkman> linkMans) {
        this.linkMans = linkMans;
    }

    /**
     * 放弃外键维护
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Linkman> linkmen = new HashSet<Linkman>();

    public Set<Linkman> getLinkmen() {
        return linkmen;
    }

    public void setLinkmen(Set<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
