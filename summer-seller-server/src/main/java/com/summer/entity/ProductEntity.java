package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 产品信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity extends BaseEntity {

    private Integer productId;
    /**
     * 分类Id
     */
    private Integer classifyId;
    /**
     * 供应商Id
     */
    private Integer factoryId;
    /**
     * 折扣
     */
    private float discount;
    /**
     * 商品名称
     */
    private Integer productName;
    /**
     * 商品图片路径
     */
    private String imagePath;
    /**
     * 价格
     */
    private float price;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 售价
     */
    private float salePrice;

    /**
     * 排序号
     */
    private Integer orderNumber;

}
