package com.liang.service;

import com.github.pagehelper.PageInfo;
import com.liang.entity.Component;

import java.util.List;

public interface ComponentService {
    // 新增组件
    public Integer save(Component component);

    // 批量删除组件
    public Integer deleteComponents(List<String> idList);

    // 删除一个组件
    public Integer deleteComponent(Integer id);

    // 查询组件
    public PageInfo<Component> selectAllComponents(int page, int limit, Component component);

    // 根据ID查询组件
    public Component selectComponentById(Integer id);

    // 批量更新，批量上架下架
    public Integer updateComponentStatus(List<Component> componentList);

    // 单独更新，编辑组件信息
    public Integer updateComponent(Component component);


}
