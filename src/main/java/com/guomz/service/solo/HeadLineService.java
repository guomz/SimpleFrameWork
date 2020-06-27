package com.guomz.service.solo;

import com.guomz.entity.bo.HeadLine;
import com.guomz.entity.dto.Result;

import java.util.List;

public interface HeadLineService {

    Result<List<HeadLine>> queryHeadLine(HeadLine condition, int pageIndex, int pageSize);
    Result<Boolean> modifyHeadLine(HeadLine condition);
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(Long lineId);
}
