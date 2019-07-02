package com.coding.business.entity;

import com.coding.business.constant.CustomerEnum;

import java.util.Objects;

/**
 * 客户实体类
 */
public class CustomerEntity {
    /** 客户姓名 */
    private String customerName;
    /** 客户卡号 */
    private String cardNo;
    /** 客户积分 */
    private double grade;
    /** 客户等级类型 */
    private CustomerEnum customerType;
    /** 等级对应积分倍数 */
    private double multiple;
    /** 客户旧等级信息 */
    private CustomerEntity oldInfo;

    /**
     * 客户实体有参构造函数
     * @param memberId
     */
    public CustomerEntity(String memberId){
        this.cardNo=memberId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public CustomerEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerEnum customerType) {
        this.customerType = customerType;
    }

    public double getMultiple() {
        return multiple;
    }

    public void setMultiple(double multiple) {
        this.multiple = multiple;
    }

    public CustomerEntity getOldInfo() {
        return oldInfo;
    }

    public void setOldInfo(CustomerEntity oldInfo) {
        this.oldInfo = oldInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Double.compare(that.grade, grade) == 0 &&
                Double.compare(that.multiple, multiple) == 0 &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(cardNo, that.cardNo) &&
                customerType == that.customerType &&
                Objects.equals(oldInfo, that.oldInfo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(customerName, cardNo, grade, customerType, multiple, oldInfo);
    }
}
