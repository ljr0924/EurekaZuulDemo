package com.liang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.entity.Component;
import com.liang.mapper.ComponentMapper;
import com.liang.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentMapper componentMapper;

    @Override
    public Integer save(Component component) {
        if (componentMapper.getCount(component).equals(1)) {
            return -1;
        }
        return componentMapper.save(component);
    }

    @Override
    public Integer deleteComponents(List<String> idList) {
        System.out.println(idList);
        return componentMapper.deleteComponents(idList);
    }

    @Override
    public Integer deleteComponent(Integer id) {
        return componentMapper.deleteComponent(id);
    }

    @Override
    public PageInfo<Component> selectAllComponents(int page, int limit, Component component) {
        PageHelper.startPage(page, limit);
        List<Component> componentList = componentMapper.selectAllComponents(component);
        return new PageInfo<>(componentList);
    }

    @Override
    public Component selectComponentById(Integer id) {
        return componentMapper.selectComponentById(id);
    }

    @Override
    public Integer updateComponentStatus(List<Component> componentList) {
        return componentMapper.updateComponentStatus(componentList);
    }

    @Override
    public Integer updateComponent(Component component) {
        // 如果重复，返回-1
        if (componentMapper.getCount(component).equals(1)) {
            return -1;
        }
        // 更新成功返回1
        if (componentMapper.updateComponent(component).equals(1)) {
            return 1;
        }
        return 0;
    }
}
