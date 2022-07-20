package com.summer.controller;

import com.summer.annotation.SystemLog;
import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import com.summer.vo.RequestParams;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/seller")
@Api(tags = "销售管理")
public class SellerController {

    /**
     * save
     *
     * @param requestParams
     * @return
     */
    @SystemLog(value = "save")
    @PostMapping(value = "save")
    public ResultData<?> save(@RequestBody RequestParams requestParams) {
        log.info("test->requestParams:{}", requestParams);
        return new ResultData<>(ResultCode.SUCCESS, requestParams);
    }

    /**
     * delete
     *
     * @param requestParams
     * @return
     */
    @SystemLog(value = "delete")
    @PostMapping(value = "delete")
    public ResultData<?> delete(@RequestBody RequestParams requestParams) {
        log.info("delete->requestParams:{}", requestParams);
        return new ResultData<>(ResultCode.SUCCESS, requestParams);
    }


    /**
     * update
     *
     * @param requestParams
     * @return
     */
    @SystemLog(value = "update")
    @PostMapping(value = "update")
    public ResultData<?> update(@RequestBody RequestParams requestParams) {
        log.info("update->requestParams:{}", requestParams);
        return new ResultData<>(ResultCode.SUCCESS, requestParams);
    }


    /**
     * info
     *
     * @param requestParams
     * @return
     */
    @SystemLog(value = "info")
    @PostMapping(value = "info")
    public ResultData<?> info(@RequestBody RequestParams requestParams) {
        log.info("info->requestParams:{}", requestParams);
        return new ResultData<>(ResultCode.SUCCESS, requestParams);
    }
}
