package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品类别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassifyEntity extends BaseEntity {
    /**
     * 分类Id
     */
    private Integer classifyId;
    /**
     * 分类名称
     */
    private Integer classifyName;
    /**
     * 父Id
     */
    private Integer parentId;
    /**
     * 排序号
     */
    private Integer orderNumber;
}
