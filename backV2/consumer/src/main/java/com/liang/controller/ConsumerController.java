package com.liang.controller;


import com.liang.commons.Result;
import com.liang.entity.Component;
import com.liang.entity.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/components/{id}")
    public Component getComponentById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject("http://provider/components/"+id, Component.class);
    }

    @GetMapping("/components/")
    public PageInfo getComponents(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  Component component) {
        String url = String.format(
                "http://provider/components/?page=%s&limit=%s&name=%s&category=%s&status=%s",
                page,
                limit,
                component.getName() != null ? component.getName() : "",
                component.getCategory() != null ? component.getCategory() : "",
                component.getStatus() != null ? component.getStatus() : ""
        );
        System.out.println(url);
        return restTemplate.getForObject(url, PageInfo.class);
    }

    @PostMapping("/components/")
    public Result addComponent(@Valid @RequestBody Component component) {
        return restTemplate.postForObject("http://provider/components/", component, Result.class);
    }

    @PutMapping("/components/{id}")
    public Result updateComponent(@PathVariable("id") Integer id, @RequestBody Component component) {
        restTemplate.put("http://provider/components/{1}", component, id);
        return new Result("更新成功", 200);
    }

    @DeleteMapping("/components/{id}")
    public Result deleteComponent(@PathVariable("id") Integer id) {
        restTemplate.delete("http://provider/components/{1}", id);
        return new Result("删除成功", 200);
    }

}
