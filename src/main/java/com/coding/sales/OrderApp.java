package com.coding.sales;

import com.coding.business.dataBase.CustomerInfo;
import com.coding.business.entity.CustomerEntity;
import com.coding.business.exception.CustomerException;
import com.coding.business.service.GradeService;
import com.coding.business.util.CalculationUtil;
import com.coding.business.util.CommonUtil;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.OrderRepresentation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) throws Exception {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) throws Exception {
        OrderRepresentation result = null;
        //计算此次客户总消费
        double amout = this.calculAmount(command.getPayments());
        //获取用户信息
        CustomerEntity customerEntity = new CustomerEntity(command.getMemberId());
        CustomerInfo.setCustomerInfo(customerEntity);
        //获取消费后最新用户等级信息
        GradeService gradeService = new GradeService();
        gradeService.getNewCustomerInfo(customerEntity,amout);


        //TODO: 请完成需求指定的功能


        return result;
    }

    /**
     * 计算此次消费总金额
     * @param payments 支付信息
     * @return
     */
    private double calculAmount(List<PaymentCommand> payments) throws CustomerException {
        if(CommonUtil.isNullOrEmpty(payments))
            throw new CustomerException("消费金额为空,请重试!");
        double amount=0d;
        for(PaymentCommand command : payments){
            if(command==null || command.getAmount()==null || command.getAmount().compareTo(BigDecimal.ZERO)<0)
                throw new CustomerException("支付金额上送有误,请检查后重新尝试!");
            amount = CalculationUtil.add(amount,command.getAmount());
        }
        return amount;
    }
}
