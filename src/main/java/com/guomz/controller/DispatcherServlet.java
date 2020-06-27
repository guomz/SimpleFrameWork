package com.guomz.controller;

import com.guomz.controller.frontend.MainPageController;
import com.guomz.controller.superadmin.HeadLineOperationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注意不要使用/*，这会拦截全部请求包括跳转到jsp的请求，导致死循环的出现
 * 该servlet模拟spring中的同名servlet，做请求统一转发
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        System.out.println(req.getServletPath());
        System.out.println(req.getMethod());

        if (req.getServletPath().equals("/frontend/mainpageinfo") && req.getMethod().equals("GET")){
            new MainPageController().getMainPageInfo(req, resp);
        }else if (req.getServletPath().equals("/superadmin/addheadline") && req.getMethod().equals("POST")){
            new HeadLineOperationController().addHeadLine(req, resp);
        }
    }

}
