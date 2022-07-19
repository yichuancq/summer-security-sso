package com.summer.controller;

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
     * @param requestParams
     */
    @PostMapping(value = "save")
    public ResultData<?> test(@RequestBody RequestParams requestParams) {
        log.info("test->requestParams:{}", requestParams);
        return new ResultData<>(ResultCode.SUCCESS, requestParams);
    }
}
