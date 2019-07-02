package com.coding.business.service;

import com.coding.business.constant.DiscountConstant;
import com.coding.business.constant.FullReductionEnum;
import com.coding.business.dataBase.MetalPool;
import com.coding.business.entity.MetalEntity;
import com.coding.business.util.CalculationUtil;
import com.coding.business.util.CommonUtil;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalculateAmt {

    public OrderRepresentation checkout(OrderCommand command){
        OrderRepresentation orderRepresentation = null;
        List<OrderItemRepresentation> orderItems = new ArrayList<OrderItemRepresentation>();
        List<DiscountItemRepresentation> discountItem = new ArrayList<DiscountItemRepresentation>();
        OrderItemRepresentation orderItemRepresentation = null;
        DiscountItemRepresentation discountItemRepresentation = null;
        BigDecimal allClassTotalPrice = BigDecimal.ZERO;
        BigDecimal totalDiscountPrice = BigDecimal.ZERO;
        List<OrderItemCommand> items = command.getItems();//订单信息
        List<String> discounts = command.getDiscounts();//折扣劵使用信息
        for(OrderItemCommand itemCommands : items){
            String productNumber = itemCommands.getProduct();//商品编号
            BigDecimal amount = itemCommands.getAmount();//商品数量

            //计算单类商品的价格
            Map<String,Object> map = getSingleClassPrice(productNumber, amount, discounts);
            orderItemRepresentation = (OrderItemRepresentation) map.get("orderItemRepresentation");//订单行
            discountItemRepresentation = (DiscountItemRepresentation) map.get("discountItemRepresentation");//折扣行
            allClassTotalPrice = allClassTotalPrice.add(orderItemRepresentation.getSubTotal());
            totalDiscountPrice = totalDiscountPrice.add(discountItemRepresentation.getDiscount());
            orderItems.add(orderItemRepresentation);
            discountItem.add(discountItemRepresentation);
        }

        orderRepresentation = new OrderRepresentation(null, null, null, null, null,
                null, 0, 0,
        orderItems, allClassTotalPrice, discountItem, totalDiscountPrice, allClassTotalPrice, null, null);

        return orderRepresentation;
    }

    /**
     * 计算单类商品的价格
     * @param number
     * @param amount
     * @return
     */
    private Map<String,Object> getSingleClassPrice(String number, BigDecimal amount, List<String> discounts){
        OrderItemRepresentation orderItemRepresentation = null;
        Map<String, MetalEntity> metalProductInfo = MetalPool.getMetalProductInfo();
        MetalEntity metalEntity = metalProductInfo.get(number);
        if(null == metalEntity){
            //抛出异常，无此商品
            throw new RuntimeException("无此商品，请检查输入参数；商品编号：" + number);
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        Double price = metalEntity.getPrice();//商品价格
        Double discount = metalEntity.getDiscount();//折扣
        List<FullReductionEnum> fullReductionEnums = metalEntity.getFullReduction();

        //价格计算方式
        int mode = getComputeMode(discount,fullReductionEnums,discounts);
        switch (mode){
            case 0:
                //无折扣、无满减
                totalPrice = new BigDecimal(CalculationUtil.multiply(price,amount));
                break;
            case 1:
                //有折扣、无满减
                totalPrice = discountMode(discounts,price,amount);
                break;
            case 2:
                //有折扣、有满减
                BigDecimal totalPrice1 = discountMode(discounts,price,amount);//折扣
                BigDecimal totalPrice2 = fullReduceMode(fullReductionEnums,price,amount);//满减
                totalPrice = totalPrice1.compareTo(totalPrice2) == 1 ? totalPrice2 : totalPrice1;//取最小值
                break;
            case 3:
                //无折扣、有满减
                totalPrice = fullReduceMode(fullReductionEnums,price,amount);
                break;
        }
        DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(metalEntity.getNumber(),metalEntity.getName(),new BigDecimal(CalculationUtil.multiply(price,amount)).subtract(totalPrice));
        orderItemRepresentation = new OrderItemRepresentation(metalEntity.getNumber(), metalEntity.getName(), new BigDecimal(metalEntity.getPrice()), amount, totalPrice);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("discountItemRepresentation",discountItemRepresentation);
        map.put("orderItemRepresentation",orderItemRepresentation);
        return map;
    }

    /**
     * 有折扣
     * @return
     */
    private BigDecimal discountMode(List<String> discounts,Double price,BigDecimal amount){
        BigDecimal singlePrice = BigDecimal.ZERO;
        BigDecimal price1 = BigDecimal.ZERO;
        BigDecimal price2 = BigDecimal.ZERO;
        for(String str : discounts){
            if("9折券".equalsIgnoreCase(str)){
                price1 = new BigDecimal(CalculationUtil.multiply(CalculationUtil.multiply(price,amount),DiscountConstant.DISCOUNTOFNINE));
            }

            if("95折券".equalsIgnoreCase(str)){
                price2 = new BigDecimal(CalculationUtil.multiply(CalculationUtil.multiply(price,amount),DiscountConstant.DISCOUNTOFNINEFIVE));
            }
        }

        if(price1.compareTo(price2) == 1){
            singlePrice = price2;//取最小值;

        }else{
            singlePrice = price1;//取最小值;
        }
//        BigDecimal disPrice = new BigDecimal(CalculationUtil.subtract(CalculationUtil.multiply(price,amount),singlePrice));
//        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
//        map.put("singlePrice",singlePrice);
//        map.put("disPrice",disPrice);
        return singlePrice;
    }

    /**
     * 有满减
     * @return
     */
    private BigDecimal fullReduceMode(List<FullReductionEnum> fullReductionEnums,Double price,BigDecimal amount){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(FullReductionEnum fullReductionEnum : fullReductionEnums){

            //满3减一
            if(FullReductionEnum.FULL3TO1 == fullReductionEnum){
                if(amount.compareTo(new BigDecimal(3)) == 1){
                    totalPrice = new BigDecimal(CalculationUtil.multiply(price,amount.subtract(BigDecimal.ONE)));
                }
            }

            //第3件半价
            if(FullReductionEnum.FULL3HALFPRICE == fullReductionEnum){
                if(amount.compareTo(new BigDecimal(3)) == 0){
                    Double price1 = CalculationUtil.multiply(price,CalculationUtil.subtract(amount,1));
                    Double price2 = CalculationUtil.multiply(price,0.5);
                    totalPrice = new BigDecimal(CalculationUtil.add(price1,price2));
                }
            }

            //每满3000元减350
            if(FullReductionEnum.FULL3000REDUCE350 == fullReductionEnum){
                totalPrice = new BigDecimal(CalculationUtil.multiply(price,amount));
                BigDecimal[] countAndRemainder = totalPrice.divideAndRemainder(new BigDecimal(3000));
                BigDecimal count = countAndRemainder[0];
                BigDecimal remainder = countAndRemainder[1];
                if(count.compareTo(BigDecimal.ZERO) == 1){
                    totalPrice = totalPrice.subtract(count.multiply(new BigDecimal(350)));
                }
                if(remainder.compareTo(new BigDecimal(2000)) == 1){
                    totalPrice = totalPrice.subtract(new BigDecimal(30));
                }else if(remainder.compareTo(new BigDecimal(1000)) == 1){
                    totalPrice = totalPrice.subtract(new BigDecimal(10));
                }
            }

            //每满2000元减30
            if(FullReductionEnum.FULL2000REDUCE30 == fullReductionEnum){
                totalPrice = new BigDecimal(CalculationUtil.multiply(price,amount));
                BigDecimal[] countAndRemainder = totalPrice.divideAndRemainder(new BigDecimal(2000));
                BigDecimal count = countAndRemainder[0];
                BigDecimal remainder = countAndRemainder[1];
                if(count.compareTo(BigDecimal.ZERO) == 1){
                    totalPrice = totalPrice.subtract(count.multiply(new BigDecimal(30)));
                }
                if(remainder.compareTo(new BigDecimal(1000)) == 1){
                    totalPrice = totalPrice.subtract(new BigDecimal(30));
                }
            }

            //每满1000元减10
            if(FullReductionEnum.FULL1000REDUCE10 == fullReductionEnum){
                totalPrice = new BigDecimal(CalculationUtil.multiply(price,amount));
                BigDecimal[] countAndRemainder = totalPrice.divideAndRemainder(new BigDecimal(1000));
                BigDecimal count = countAndRemainder[0];
                BigDecimal remainder = countAndRemainder[1];
                if(count.compareTo(BigDecimal.ZERO) == 1){
                    totalPrice = totalPrice.subtract(count.multiply(new BigDecimal(10)));
                }
            }

        }

//        BigDecimal disPrice = new BigDecimal(CalculationUtil.subtract(CalculationUtil.multiply(price,amount),totalPrice));
//        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
//        map.put("singlePrice",totalPrice);
//        map.put("disPrice",disPrice);
        return totalPrice;
    }

    /**
     * 获取金额计算类型
     * @param discount
     * @param fullReductionEnums
     * @param discounts
     * @return
     */
    private int getComputeMode(Double discount, List<FullReductionEnum> fullReductionEnums, List<String> discounts){
        int mode = 0;
        //商品无折扣、无满减
        if(CommonUtil.isNullOrEmpty(discount) && CommonUtil.isNullOrEmpty(fullReductionEnums)){
            return mode;//商品无折扣、无满减
        }

        //商品有折扣、无满减
        if(!CommonUtil.isNullOrEmpty(discount) && CommonUtil.isNullOrEmpty(fullReductionEnums)){
            //客户使用折扣劵
            if(!CommonUtil.isNullOrEmpty(discounts)){
                mode = 1;//商品有折扣、无满减、客户使用折扣劵
            }else{
                mode = 0;//商品有折扣、无满减、客户没有使用折扣劵
            }
        }

        //商品有折扣、有满减
        if(!CommonUtil.isNullOrEmpty(discount) && !CommonUtil.isNullOrEmpty(fullReductionEnums)){
            if(!CommonUtil.isNullOrEmpty(discounts)){
                mode = 2;//商品有折扣、有满减、客户使用折扣劵
            }else{
                mode = 3;//商品有折扣、有满减、客户没有使用折扣劵
            }
        }
        return mode;
    }
}
