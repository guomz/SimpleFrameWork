package com.guomz.service.solo.impl;

import com.guomz.entity.bo.ShopCategory;
import com.guomz.entity.dto.Result;
import com.guomz.service.solo.ShopCategoryService;
import com.guomz.simpleframework.core.annotations.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory condition, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory condition) {
        return null;
    }

    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(Long shopCategoryId) {
        return null;
    }
}
