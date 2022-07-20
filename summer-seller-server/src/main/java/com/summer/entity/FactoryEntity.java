package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 供应商信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FactoryEntity extends BaseEntity {
    /**
     * 供应商Id
     */
    private Integer factoryId;
    /**
     * 供应商名称
     */
    private Integer factoryName;
    /**
     * 供应商联系人
     */
    private String contractMan;
    /**
     * 供应商联系人电话
     */
    private String contractPhone;
    /**
     * 供应商联地址
     */
    private String contractAddress;
}

