package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 物流实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogisticsEntity extends BaseEntity {
    /**
     * 物流id
     */
    private Integer logisticsId;
    /**
     * 物流名称
     */
    private String logisticsName;
    /**
     * 联系人
     */
    private String contractMan;
    /**
     * 联系人电话
     */
    private String contractPhone;
    /**
     * 联系地址
     */
    private String contractAddress;
}
