package com.liang.controller;


import com.github.pagehelper.PageInfo;
import com.liang.commons.Result;
import com.liang.entity.Component;
import com.liang.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private ComponentService componentService;

    // 查询一个组件
    @GetMapping("/components/{id}")
    public Component getComponentById(@PathVariable("id") Integer id) {
        return componentService.selectComponentById(id);
    }

    // 查询多个组件
    @GetMapping("/components/")
    public PageInfo<Component> getComponents(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
                                             Component component) {
        System.out.println(component);
        return componentService.selectAllComponents(page, limit, component);
    }

    // 新增组件
    @PostMapping("/components/")
    public Result addComponent(@Valid @RequestBody Component component) {
        System.out.println(component);
        Integer result = componentService.save(component);
        if (result.equals(1)) {
            return new Result("添加成功", 200);
        } else if (result.equals(-1)) {
            return new Result("已存在组件", 400);
        } else {
            return new Result("填写完整字段", 400);
        }
    }

    // 删除组件
    @DeleteMapping("/components/{id}")
    public Result deleteComponent(@PathVariable("id") Integer id) {
        Integer result = componentService.deleteComponent(id);
        if (result.equals(1)) {
            return new Result("删除成功", 200);
        }
        return new Result("删除失败", 400);
    }

    // 更新组件
    @PutMapping("/components/{id}")
    public Result updateComponent(@RequestBody Component component) {
        Integer result = componentService.updateComponent(component);
        if (result.equals(1)) {
            return new Result("更新成功", 200);
        } else if (result.equals(-1)) {
            return new Result("该组件已存在", 400);
        } else {
            return new Result("更新失败", 400);
        }
    }

    // 批量更新状态
    @PutMapping("/components/status")
    public Result updateComponent(@RequestBody List<Component> componentList) {
        System.out.println(componentList);
        Integer result = componentService.updateComponentStatus(componentList);
        if (result.equals(1)) {
            return new Result("更新成功", 200);
        }
        return new Result("更新失败", 400);
    }

    // 批量删除组件
    @DeleteMapping("/components/")
    public Result deleteComponents(@RequestBody List<String> idList) {
        System.out.println(idList);
        Integer result = componentService.deleteComponents(idList);
        if (result.equals(idList.size())) {
            return new Result("删除成功", 200);
        }
        return new Result("删除失败", 400);
    }


}
