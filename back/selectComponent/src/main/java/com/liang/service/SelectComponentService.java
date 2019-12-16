package com.liang.service;

import com.github.pagehelper.PageInfo;
import com.liang.entity.Component;

import java.util.List;

public interface SelectComponentService {

    public PageInfo<Component> selectAllComponents(int page, int limit, Component component);

    public Component selectComponentById(Integer id);

}
