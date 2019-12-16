package com.liang.controller;


import com.liang.commons.Result;
import com.liang.entity.Component;
import com.liang.service.UpdateComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UpdateComponentController {

    @Autowired
    private UpdateComponentService updateComponentService;

    @PutMapping("/components/")
    public Result updateComponent(@RequestBody Component component) {
        Integer result = updateComponentService.updateComponent(component);
        if(result.equals(1)) {
            return new Result("更新成功", 200);
        } else if (result.equals(-1)) {
            return new Result("该组件已存在", 400);
        } else {
            return new Result("更新失败", 400);
        }

    }

    @PutMapping("/components/status")
    public Result updateComponent(@RequestBody List<Component> componentList) {
        System.out.println(componentList);
        Integer result = updateComponentService.updateComponentStatus(componentList);
        if (result.equals(1)) {
            return new Result("更新成功", 200);
        }
        return new Result("更新失败", 400);
    }

}
