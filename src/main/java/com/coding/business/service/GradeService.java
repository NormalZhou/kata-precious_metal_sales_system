package com.coding.business.service;

import com.coding.business.dataBase.CustomerGrade;
import com.coding.business.entity.CustomerEntity;
import com.coding.business.util.CalculationUtil;

/**
 * 积分服务类
 */
public class GradeService {

    /**
     * 计算积分，返回最新积分情况
     * @param customerEntity 客户信息
     * @param amount 此次消费金额
     * @return
     */
    public double calculateGrade(CustomerEntity customerEntity,double amount){
        //计算此次积分获取情况
        double getGrade = CalculationUtil.multiply(customerEntity.getMultiple(),amount);
        //计算加总后新积分情况
        double newGrade = CalculationUtil.add(customerEntity.getGrade(),getGrade);
        return newGrade;
    }

    /**
     * 根据用户消费，获取新用户等级信息
     * @return
     */
    public CustomerEntity getNewCustomerInfo(CustomerEntity oldCustomer,double amount){
        CustomerEntity customer = new CustomerEntity(oldCustomer.getCardNo());//设置用户卡号
        customer.setCustomerName(oldCustomer.getCustomerName());//设置用户姓名
        customer.setOldInfo(oldCustomer);//设置用户升级之前等级信息
        //获取用户最新积分情况
        double newGrade = this.calculateGrade(oldCustomer,amount);
        customer.setGrade(newGrade);//设置用户最新积分情况
        CustomerGrade.setCustomerGrade(customer);//模拟数据库数据，根据最新积分设置用户的等级信息
        return customer;
    }
}
