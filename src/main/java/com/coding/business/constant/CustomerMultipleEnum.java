package com.coding.business.constant;

/**
 * 客户等级倍数枚举类
 */
public enum CustomerMultipleEnum {
    /** 普卡 1倍 */
    NORMAL("NORMAL",1d),
    /** 金卡 1.5倍 */
    GOLDEN("GOLDEN",1.5d),
    /** 白金卡 1.8倍 */
    PLATINUM("PLATINUM",1.8d),
    /** 钻石卡 2倍 */
    DIAMOND("DIAMOND",2d);

    /** 客户等级 */
    private String name;
    /** 积分倍数 */
    private Double multiple;
    /** 构造函数 */
    private CustomerMultipleEnum(String name, Double multiple) {
        this.name = name;
        this.multiple = multiple;
    }

    /**
     * 根据客户等级获取积分倍数
     * @param name 客户等级
     * @return
     */
    public static Double getMultiple(CustomerEnum name) {
        for (CustomerMultipleEnum c : CustomerMultipleEnum.values()) {
            if (c.getName().equals(name)) {
                return c.multiple;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMultiple() {
        return multiple;
    }

    public void setMultiple(Double multiple) {
        this.multiple = multiple;
    }
}
