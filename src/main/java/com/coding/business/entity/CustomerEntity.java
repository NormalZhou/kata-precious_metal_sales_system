package com.coding.business.entity;

import com.coding.business.constant.CustomerEnum;

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
}
