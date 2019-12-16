package com.liang.service;

import com.liang.entity.Component;

import java.util.List;

public interface UpdateComponentService {

    // 批量更新，批量上架下架
    public Integer updateComponentStatus(List<Component> componentList);
    // 单独更新，编辑组件信息
    public Integer updateComponent(Component component);
}
