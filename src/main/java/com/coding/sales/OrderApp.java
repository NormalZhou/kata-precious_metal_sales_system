package com.coding.sales;

import com.coding.business.dataBase.CustomerInfo;
import com.coding.business.entity.CustomerEntity;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.OrderRepresentation;

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
        //获取用户信息
        CustomerEntity customerEntity = new CustomerEntity(command.getMemberId());
        CustomerInfo.setCustomerInfo(customerEntity);


        //TODO: 请完成需求指定的功能


        return result;
    }
}
