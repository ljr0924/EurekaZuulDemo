package com.liang.controller;


import com.github.pagehelper.PageInfo;
import com.liang.entity.Component;
import com.liang.service.SelectComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectController {

    @Autowired
    private SelectComponentService selectComponentService;

    @GetMapping("/components/{id}")
    public Component getComponentById(@PathVariable("id") Integer id) {
        return selectComponentService.selectComponentById(id);
    }

//    @GetMapping("/components/")
//    public PageInfo<Component> getComponents(@RequestParam(value = "page", defaultValue = "1") int page,
//                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
//                                             @RequestParam(value = "name", defaultValue = "") String name,
//                                             @RequestParam(value = "category", defaultValue = "") String category,
//                                             @RequestParam(value = "status", defaultValue = "") String status) {
//        if (status.equals("上架")) {
//            status = "1";
//        } else if (status.equals("下架")) {
//            status = "0";
//        }
//        return selectComponentService.selectAllComponents(page, limit, name, category, status);
//    }
    @GetMapping("/components/")
    public PageInfo<Component> getComponents(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
                                             Component component) {
        System.out.println(component);
        return selectComponentService.selectAllComponents(page, limit, component);
    }

}
