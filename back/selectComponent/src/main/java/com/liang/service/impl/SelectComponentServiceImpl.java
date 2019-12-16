package com.liang.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.entity.Component;
import com.liang.mapper.SelectComponentMapper;
import com.liang.service.SelectComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectComponentServiceImpl implements SelectComponentService {

    @Autowired
    private SelectComponentMapper selectComponentMapper;

    @Override
    public PageInfo<Component> selectAllComponents(int page, int limit, Component component) {
        PageHelper.startPage(page, limit);
        List<Component> componentList = selectComponentMapper.selectAllComponents(component);
        return new PageInfo<>(componentList);
    }

    @Override
    public Component selectComponentById(Integer id) {
        return selectComponentMapper.selectComponentById(id);
    }
}
