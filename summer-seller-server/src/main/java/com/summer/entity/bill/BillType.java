package com.summer.entity.bill;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 单据类别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillType extends BaseEntity {
    /**
     * 类别Id
     */
    private Integer billTypeId;
    /**
     * 类别名称
     */
    private String billTypeName;
    /**
     * 父类型
     */
    private Integer parentId;
}
