package com.summer.oath.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("LoginResultVo返回值")
public class LoginResultVo {
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;


    public LoginResultVo(Integer userId, String userName, String token) {
        this.userName = userName;
        this.userId = userId;
        this.token = token;
    }
}
