package com.peait.peaituser.controller;

import com.peait.peaituser.result.Result;
import com.peait.peaituser.service.MenuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MenuListController {
    @Autowired
    private MenuListService menuListService;

    @GetMapping("/getMenu")
    @CrossOrigin
    public Result getEmnuList(HttpServletRequest request){
        return menuListService.queryEmnuList(request.getHeader("authorization"));
    }
}
