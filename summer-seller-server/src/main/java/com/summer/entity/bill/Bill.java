package com.summer.entity.bill;

import com.summer.entity.base.BaseEntity;
import lombok.Data;

/**
 * 单据
 */

@Data
public class Bill extends BaseEntity {
    /**
     * 单据号
     */
    private Integer billId;
    /**
     * 单据类型Id
     */
    private String billTypeId;
}
