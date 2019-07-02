package com.coding.business.dataBase;

import com.coding.business.constant.DiscountConstant;
import com.coding.business.constant.FullReductionEnum;
import com.coding.business.constant.UnitConstant;
import com.coding.business.entity.MetalEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 贵金属商品列表信息
 */
public class MetalPool {
    private List<MetalEntity> list;

    /**
     * 获取贵金属商品列表信息
     * @return
     */
    public static Map<String,MetalEntity> getMetalProductList(){
        Map<String,MetalEntity> map = new HashMap<String,MetalEntity>();
        MetalEntity MetalEntity1 = new MetalEntity();
        MetalEntity1.setName("世园会五十国钱币册");
        MetalEntity1.setNumber("001001");
        MetalEntity1.setUnit(UnitConstant.BOOK);
        MetalEntity1.setPrice(998.00);
        map.put("001001",MetalEntity1);

        MetalEntity MetalEntity2 = new MetalEntity();
        MetalEntity2.setName("2019北京世园会纪念银章大全40g");
        MetalEntity2.setNumber("001002");
        MetalEntity2.setUnit(UnitConstant.BOX);
        MetalEntity2.setPrice(1380.00);
        MetalEntity2.setDiscount(DiscountConstant.DISCOUNTOFNINE);
        map.put("001002",MetalEntity2);

        MetalEntity MetalEntity3 = new MetalEntity();
        MetalEntity3.setName("招财进宝");
        MetalEntity3.setNumber("003001");
        MetalEntity3.setUnit(UnitConstant.ITEM);
        MetalEntity3.setPrice(1580.00);
        MetalEntity3.setDiscount(DiscountConstant.DISCOUNTOFNINEFIVE);
        map.put("003001",MetalEntity3);

        MetalEntity MetalEntity4 = new MetalEntity();
        MetalEntity4.setName("水晶之恋");
        MetalEntity4.setNumber("003002");
        MetalEntity4.setUnit(UnitConstant.ITEM);
        MetalEntity4.setPrice(980.00);
        List<FullReductionEnum> fullReductions4 = new ArrayList<FullReductionEnum>();
        fullReductions4.add(FullReductionEnum.FULL1000REDUCE10);
        MetalEntity4.setFullReduction(fullReductions4);
        map.put("003002",MetalEntity4);

        MetalEntity MetalEntity5 = new MetalEntity();
        MetalEntity5.setName("中国经典钱币套装");
        MetalEntity5.setNumber("002002");
        MetalEntity5.setUnit(UnitConstant.SUIT);
        MetalEntity5.setPrice(998.00);
        List<FullReductionEnum> fullReductions5 = new ArrayList<FullReductionEnum>();
        fullReductions5.add(FullReductionEnum.FULL2000REDUCE30);
        fullReductions5.add(FullReductionEnum.FULL1000REDUCE10);
        MetalEntity5.setFullReduction(fullReductions5);
        map.put("002002",MetalEntity5);

        MetalEntity MetalEntity6 = new MetalEntity();
        MetalEntity6.setName("守扩之羽比翼双飞4.8g");
        MetalEntity6.setNumber("002001");
        MetalEntity6.setUnit(UnitConstant.ITEM);
        MetalEntity6.setPrice(1080.00);
        List<FullReductionEnum> fullReductions6 = new ArrayList<FullReductionEnum>();
        fullReductions6.add(FullReductionEnum.FULL3HALFPRICE);
        fullReductions6.add(FullReductionEnum.FULL3TO1);
        MetalEntity6.setFullReduction(fullReductions6);
        MetalEntity6.setDiscount(DiscountConstant.DISCOUNTOFNINEFIVE);
        map.put("002001",MetalEntity6);

        MetalEntity MetalEntity7 = new MetalEntity();
        MetalEntity7.setName("中国银象棋12g");
        MetalEntity7.setNumber("002003");
        MetalEntity7.setUnit(UnitConstant.SUIT);
        MetalEntity7.setPrice(698.00);
        List<FullReductionEnum> fullReductions7 = new ArrayList<FullReductionEnum>();
        fullReductions7.add(FullReductionEnum.FULL3000REDUCE350);
        fullReductions7.add(FullReductionEnum.FULL2000REDUCE30);
        fullReductions7.add(FullReductionEnum.FULL1000REDUCE10);
        MetalEntity7.setFullReduction(fullReductions7);
        MetalEntity7.setDiscount(DiscountConstant.DISCOUNTOFNINE);
        map.put("002003",MetalEntity2);

        return map;
    }
}
