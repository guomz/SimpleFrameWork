package com.guomz.controller.frontend;

import com.guomz.entity.dto.MainPageInfoDto;
import com.guomz.entity.dto.Result;
import com.guomz.service.combine.HeadLineShopCategoryCombineService;
import com.guomz.simpleframework.annotations.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class MainPageController {

    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDto> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
