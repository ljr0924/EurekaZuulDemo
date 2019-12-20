package com.liang.controller;


import com.liang.commons.Result;
import com.liang.entity.Component;
import com.liang.service.AddComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AddComponentController {

    @Autowired
    private AddComponentService addComponentService;

    @PostMapping("/components")
    public Result addComponent(@Valid @RequestBody Component component) {
        System.out.println(component);
        Integer result = addComponentService.save(component);
        if (result.equals(1)) {
            return new Result("添加成功", 200);
        } else if (result.equals(-1)){
            return new Result("已存在组件", 400);
        } else {
            return new Result("填写完整字段", 400);
        }
    }
}
