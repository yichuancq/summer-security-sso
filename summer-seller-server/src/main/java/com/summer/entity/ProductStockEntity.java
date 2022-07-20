package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品库存信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStockEntity extends BaseEntity {
    /**
     * id
     */
    private Integer productStockId;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 库存Id
     */
    private Integer stockId;
    /**
     * 库存量
     */
    private Integer stockAmount;
}
