package com.summer.entity;

import com.summer.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 店面信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shop extends BaseEntity {

    private Integer shopId;
    /**
     * 连锁店名称
     */
    private String shopName;
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
