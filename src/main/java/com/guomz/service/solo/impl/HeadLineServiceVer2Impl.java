package com.guomz.service.solo.impl;

import com.guomz.entity.bo.HeadLine;
import com.guomz.entity.dto.Result;
import com.guomz.service.solo.HeadLineService;
import com.guomz.simpleframework.core.annotations.Service;

import java.util.List;

/**
 * 用于测试一个接口多实现类的注入情况
 */
@Service
public class HeadLineServiceVer2Impl implements HeadLineService {
    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine condition, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine condition) {
        return null;
    }

    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(Long lineId) {
        return null;
    }
}
