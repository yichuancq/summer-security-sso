package com.summer.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义分页请求Dto
 *
 * @author yichuan
 */
@ApiModel("自定义分页请求Dto请求参数")
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest implements Serializable {
    /***
     *页索引
     */
    @ApiModelProperty(value = "页索引", required = true)
    private Integer pageNumber = 0;
    /***
     *每页大小
     */
    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize = 20;

    @Override
    public String toString() {
        return "PageRequest{" + "pageNumber=" + pageNumber + ", pageSize=" + pageSize + '}';
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
