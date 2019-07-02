package com.coding.business.util;

import java.math.BigDecimal;

/**
 * 数据计算工具类
 */
public class CalculationUtil {

    /**
     * 相加
     * @param v1
     * @param v2
     * @return double
     */
    public static Double add(Object v1, Object v2) {
        BigDecimal n1 = objToDecimal(v1);
        BigDecimal n2 = objToDecimal(v2);
        return n1.add(n2).doubleValue();
    }

    /**
     * 相减
     * @param v1
     * @param v2
     * @return double
     */
    public static Double subtract(Object v1, Object v2) {
        BigDecimal n1 = objToDecimal(v1);
        BigDecimal n2 = objToDecimal(v2);
        return n1.subtract(n2).doubleValue();
    }

    /**
     * 相乘
     * @param v1
     * @param v2
     * @return double
     */
    public static Double multiply(Object v1, Object v2) {
        BigDecimal n1 = objToDecimal(v1);
        BigDecimal n2 = objToDecimal(v2);
        return n1.multiply(n2).doubleValue();
    }

    /**
     * 相除v1/v2
     * @param v1
     * @param v2
     * @return double
     */
    public static Double divide(Object v1, Object v2) {
        BigDecimal n1 = objToDecimal(v1);
        BigDecimal n2 = objToDecimal(v2);
        return n1.divide(n2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 比较大小 小于0：v1 < v2 大于0：v1 > v2 等于0：v1 = v2
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(Object v1, Object v2) {
        BigDecimal n1 = objToDecimal(v1);
        BigDecimal n2 = objToDecimal(v2);
        return n1.compareTo(n2);
    }

    /**
     * 将不同类型的数字转换为bigDecimal
     * @param obj
     * @return
     */
    private static BigDecimal objToDecimal(Object obj){
        if(obj instanceof Integer){
            return new BigDecimal((Integer)obj);
        }else if(obj instanceof Double){
            return new BigDecimal(Double.toString((Double)obj));
        }else if(obj instanceof BigDecimal){
            return (BigDecimal)obj;
        }
        return new BigDecimal(0);//默认返回0
    }
}
