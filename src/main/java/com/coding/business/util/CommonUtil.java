package com.coding.business.util;

import java.util.List;
/**
 * 通用工具类
 */
public class CommonUtil {

    /**
     * 判断对象是否为空(对象、字符串、集合)
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)
            return true;
        if(obj instanceof String){
            if("".equals(((String) obj).trim()))
                return true;
        }else if(obj instanceof List){
            if(((List) obj).size()==0)
                return true;
        }
        return false;
    }
}
