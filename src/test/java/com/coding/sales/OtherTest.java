package com.coding.sales;

import com.coding.business.constant.CustomerEnum;
import com.coding.business.constant.CustomerMultipleEnum;
import com.coding.business.dataBase.CustomerInfo;
import com.coding.business.entity.CustomerEntity;
import com.coding.business.util.CalculationUtil;
import com.coding.business.util.CommonUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OtherTest {
    /**
     * 精度计算工具类加法乘法测试
     */
    @Test
    public void calculationTest(){
        assertEquals(CalculationUtil.add(12,13.14d),CalculationUtil.add(12.00d,13.14d));
        assertEquals(CalculationUtil.add(2,3.10d), new Double("5.10"));
        assertEquals(CalculationUtil.multiply(2d,3.50d),CalculationUtil.multiply(2.00d,new BigDecimal("3.5")));
        assertEquals(CalculationUtil.multiply(2d,3.50d),new Double("7.00"));
    }

    /**
     * 通用工具类-空对象判定测试
     */
    @Test
    public void commonUtilTest(){
        assertTrue(CommonUtil.isNullOrEmpty("  "));
        assertTrue(CommonUtil.isNullOrEmpty(null));
        assertTrue(CommonUtil.isNullOrEmpty(new ArrayList()));
        assertFalse(CommonUtil.isNullOrEmpty(new HashMap<>()));
    }

    @Test
    public void CustomerMultipleTest(){
        assertEquals(CustomerMultipleEnum.getMultiple(CustomerEnum.NORMAL),Double.valueOf(1));
        assertEquals(CustomerMultipleEnum.getMultiple(CustomerEnum.GOLDEN),Double.valueOf(1.5));
        assertEquals(CustomerMultipleEnum.getMultiple(CustomerEnum.PLATINUM),Double.valueOf(1.8));
        assertEquals(CustomerMultipleEnum.getMultiple(CustomerEnum.DIAMOND),Double.valueOf(2));
    }

    /**
     * 用户信息获取测试
     */
    @Test
    public void customerInfoTest(){
        //马丁 普卡,6236609999,9860
        CustomerEntity mading = new CustomerEntity("6236609999");
        mading.setGrade(9860);
        mading.setCustomerName("马丁");
        mading.setCustomerType(CustomerEnum.NORMAL);
        mading.setMultiple(1);
        //王立,金卡,6630009999,48860
        CustomerEntity wangli = new CustomerEntity("6630009999");
        wangli.setGrade(48860);
        wangli.setCustomerName("王立");
        wangli.setCustomerType(CustomerEnum.GOLDEN);
        wangli.setMultiple(1.5);
        //李想,白金卡,8230009999,98860
        CustomerEntity lixiang = new CustomerEntity("8230009999");
        lixiang.setGrade(98860);
        lixiang.setCustomerName("李想");
        lixiang.setCustomerType(CustomerEnum.PLATINUM);
        lixiang.setMultiple(1.8);
        //张三,钻石卡,9230009999,198860
        CustomerEntity zhangsan = new CustomerEntity("9230009999");
        zhangsan.setGrade(198860);
        zhangsan.setCustomerName("张三");
        zhangsan.setCustomerType(CustomerEnum.DIAMOND);
        zhangsan.setMultiple(2);

        try {
            assertTrue(mading.equals(CustomerInfo.setCustomerInfo(new CustomerEntity("6236609999"))));
            assertEquals(wangli,CustomerInfo.setCustomerInfo(new CustomerEntity("6630009999")));
            assertEquals(lixiang,CustomerInfo.setCustomerInfo(new CustomerEntity("8230009999")));
            assertEquals(zhangsan,CustomerInfo.setCustomerInfo(new CustomerEntity("9230009999")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
