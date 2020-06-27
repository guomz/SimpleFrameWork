package com.guomz.service.solo;

import com.guomz.entity.bo.ShopCategory;
import com.guomz.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {

    Result<List<ShopCategory>> queryShopCategory(ShopCategory condition, int pageIndex, int pageSize);
    Result<Boolean> modifyShopCategory(ShopCategory condition);
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(Long shopCategoryId);
}
