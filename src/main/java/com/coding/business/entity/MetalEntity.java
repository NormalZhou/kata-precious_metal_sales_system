package com.coding.business.entity;

import com.coding.business.constant.FullReductionEnum;

import java.util.List;

/**
 * 贵金属实体类
 */
public class MetalEntity {
    private String name;//名称
    private String number;//编号
    private String unit;//单位
    private Double price;//价格
    private Double discount;//折扣
    private List<FullReductionEnum> fullReduction;//满减活动

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<FullReductionEnum> getFullReduction() {
        return fullReduction;
    }

    public void setFullReduction(List<FullReductionEnum> fullReduction) {
        this.fullReduction = fullReduction;
    }
}
