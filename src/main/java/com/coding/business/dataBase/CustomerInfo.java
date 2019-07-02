package com.coding.business.dataBase;

import com.coding.business.constant.CustomerEnum;
import com.coding.business.constant.CustomerMultipleEnum;
import com.coding.business.entity.CustomerEntity;
import com.coding.business.exception.CustomerException;
import com.coding.business.util.CommonUtil;

/**
 * 模拟数据库数据，用于根据积分获取用户等级信息
 */
public class CustomerInfo {
    /**
     * 根据用户编号获取用户信息
     * @param customerEntity
     * @throws CustomerException
     */
    public static void setCustomerInfo(CustomerEntity customerEntity) throws Exception{
        //获取用户卡号
        String cardNo = customerEntity.getCardNo();
        //此部分模拟数据库根据卡号查询用户信息
        if(CommonUtil.isNullOrEmpty(cardNo)){
            throw new CustomerException("用户卡号信息不允许为空");
        }

        if("6236609999".equals(cardNo)){
            customerEntity.setCustomerName("马丁");//设置客户姓名
            customerEntity.setGrade(9860);//设置积分
            customerEntity.setCustomerType(CustomerEnum.NORMAL);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.NORMAL));//设置客户登记对应积分倍数
        }else if("6630009999".equals(cardNo)){
            customerEntity.setCustomerName("王立");
            customerEntity.setGrade(48860);//设置积分
            customerEntity.setCustomerType(CustomerEnum.GOLDEN);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.GOLDEN));//设置客户登记对应积分倍数
        }else if("8230009999".equals(cardNo)){
            customerEntity.setCustomerName("李想");
            customerEntity.setGrade(98860);//设置积分
            customerEntity.setCustomerType(CustomerEnum.PLATINUM);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.PLATINUM));//设置客户登记对应积分倍数
        }else if("9230009999".equals(cardNo)){
            customerEntity.setCustomerName("张三");
            customerEntity.setGrade(198860);//设置积分
            customerEntity.setCustomerType(CustomerEnum.DIAMOND);//设置客户等级类型
            customerEntity.setMultiple(CustomerMultipleEnum.getMultiple(CustomerEnum.DIAMOND));//设置客户登记对应积分倍数
        }else{
            throw new CustomerException("当前用户不存在，请核实卡号信息重新输入");
        }
    }
}
