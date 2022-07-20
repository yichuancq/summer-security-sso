package com.summer.entity;

import com.summer.entity.bill.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 货运单号
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrackingBill extends Bill {
    /**
     * 商品包数
     */
    private Integer packAmount;
    /**
     * 商品品种数
     */
    private Integer count;
    /**
     * 经手人id
     */
    private Integer userId;
    /**
     * 经手人名称
     */
    private String userName;
    /**
     * 是否审核（1已审核,0未审核）
     */
    private String isVerify;
    /**
     * 检货时间
     */
    private Integer checkUserId;
    /**
     * 检货人姓名
     */
    private String checkUserName;
    /**
     * 审核意见
     */
    private String verifyReason;
    /**
     * 经手人id
     */
    private Integer verifyUserId;
    /**
     * 经手人名称
     */
    private String verifyUserName;
    /**
     * 审核时间
     */
    private Date verifyTime;
    /**
     * 到货时间
     */
    private Date arrivalTime;
    /**
     * 检货时间
     */
    private Date checkTime;
}
