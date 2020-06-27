package com.guomz.entity.dto;

import com.guomz.entity.bo.HeadLine;
import com.guomz.entity.bo.ShopCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageInfoDto {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
