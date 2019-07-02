package com.coding.business.dataBase;

import com.coding.business.constant.CustomerEnum;
import com.coding.business.constant.CustomerMultipleEnum;
import com.coding.business.entity.CustomerEntity;

import java.util.Map;

/**
 * 模拟数据库数据，用于根据积分获取用户等级信息
 */
public class CustomerGrade {
    /** 普卡金额上限 */
    private static final int normalMaxList = 10000;
    /** 金卡金额上限 */
    private static final int goldenMaxList = 50000;
    /** 白金卡金额上限 */
    private static final int platinumMaxList = 100000;

    /**
     * 模拟数据库查询，根据区间获取等级信息
     * @return
     */
    public static void getCustomerGradeMap(CustomerEntity customerEntity){
        //获取用户分数
        double grade = customerEntity.getGrade();
        //模拟数据库查询，获取等级信息
        if(grade<normalMaxList){
            //普卡
            customerEntity.setCustomerType(CustomerEnum.NORMAL);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.NORMAL));//设置客户登记对应积分倍数
        }else if(grade<goldenMaxList){
            //金卡
            customerEntity.setCustomerType(CustomerEnum.GOLDEN);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.GOLDEN));//设置客户登记对应积分倍数
        }else if(grade<platinumMaxList){
            //白金卡
            customerEntity.setCustomerType(CustomerEnum.PLATINUM);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.PLATINUM));//设置客户登记对应积分倍数
        }else{
            //钻石卡
            customerEntity.setCustomerType(CustomerEnum.DIAMOND);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.DIAMOND));//设置客户登记对应积分倍数
        }
    }
}
