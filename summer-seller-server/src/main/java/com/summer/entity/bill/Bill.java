package com.summer.entity.bill;

import com.summer.entity.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 单据
 */

@Data
@ToString
public class Bill extends BaseEntity {
    /**
     * 单据号
     */
    private Integer billId;
    /**
     * 单据名称
     */
    private String billName;
    /**
     * 单据类型Id
     */
    private String billTypeId;
}
