package com.liang.mapper;

import com.liang.entity.Component;

import java.util.List;

public interface UpdateComponentMapper {

    public Integer updateComponentStatus(List<Component> componentList);
    public Integer updateComponent(Component component);
    public Integer getCount(Component component);
}