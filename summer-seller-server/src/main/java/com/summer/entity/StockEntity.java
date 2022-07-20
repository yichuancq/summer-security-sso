package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 库存信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockEntity extends BaseEntity {
    /**
     * 库存id
     */
    private Integer stockId;
    /**
     * 库存名称
     */
    private String stockName;
    /**
     * 排序号
     */
    private Integer orderNumber;
}
