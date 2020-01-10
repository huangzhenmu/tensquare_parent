package com.hzm.client.impl;

import com.hzm.client.LabelClient;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"熔断器启动了");
    }
}
