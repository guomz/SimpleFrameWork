package com.guomz.service.solo.impl;

import com.guomz.service.solo.HeadLineService;
import com.guomz.entity.bo.HeadLine;
import com.guomz.entity.dto.Result;
import com.guomz.simpleframework.annotations.Service;

import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
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
