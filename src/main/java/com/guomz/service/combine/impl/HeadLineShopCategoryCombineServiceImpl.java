package com.guomz.service.combine.impl;

import com.guomz.entity.bo.HeadLine;
import com.guomz.entity.bo.ShopCategory;
import com.guomz.entity.dto.MainPageInfoDto;
import com.guomz.entity.dto.Result;
import com.guomz.service.combine.HeadLineShopCategoryCombineService;
import com.guomz.service.solo.HeadLineService;
import com.guomz.service.solo.ShopCategoryService;
import com.guomz.simpleframework.core.annotations.Service;
import com.guomz.simpleframework.core.inject.annotations.Autowired;

import java.util.List;
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired(value = "HeadLineServiceImpl")
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    /**
     * 获取首页信息，包括头条与店铺类别
     * @return
     */
    @Override
    public Result<MainPageInfoDto> getMainPageInfo() {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);
        //查询全部顶级类别
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
        return mergPageInfo(headLineResult, shopCategoryResult);
    }

    private Result<MainPageInfoDto> mergPageInfo(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult){
        return null;
    }
}
