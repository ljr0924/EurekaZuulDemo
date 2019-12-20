package com.liang.mapper;


import com.liang.entity.Component;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComponentMapper {
    // 新增组件
    public Integer save(Component component);
    // 查询组件数量
    public Integer getCount(Component component);
    // 删除组件
    public Integer deleteComponents(List<String> idList);
    // 删除一个组件
    public Integer deleteComponent(Integer id);
    // 查询所有组件
    public List<Component> selectAllComponents(Component component);
    // 根据ID查询组件
    public Component selectComponentById(Integer id);
    // 批量更新组件状态
    public Integer updateComponentStatus(List<Component> componentList);
    // 更新组件信息
    public Integer updateComponent(Component component);
}
