package com.liang.mapper;

import com.liang.entity.Component;


public interface AddComponentMapper {

    public Integer save(Component component);
    public Integer getCount(Component component);
}
