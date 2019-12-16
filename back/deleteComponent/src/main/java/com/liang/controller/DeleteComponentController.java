package com.liang.controller;

import com.liang.commons.Result;
import com.liang.service.DeleteComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DeleteComponentController {

    @Autowired
    private DeleteComponentService deleteComponentService;

    @DeleteMapping("/components")
    public Result deleteComponent(@RequestBody List<String> nameList) {
        System.out.println(nameList);
        Integer result = deleteComponentService.deleteComponent(nameList);
        if (result.equals(nameList.size())) {
            return new Result("删除成功", 200);
        }
        return new Result("删除失败", 400);
    }

}
