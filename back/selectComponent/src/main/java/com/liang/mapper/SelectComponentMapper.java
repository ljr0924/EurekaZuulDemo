package com.liang.mapper;


import com.liang.entity.Component;

import java.util.List;

public interface SelectComponentMapper {

    public List<Component> selectAllComponents(Component component);

    public Component selectComponentById(Integer id);

}
