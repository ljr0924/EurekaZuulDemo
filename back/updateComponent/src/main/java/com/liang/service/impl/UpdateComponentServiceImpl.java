package com.liang.service.impl;


import com.liang.entity.Component;
import com.liang.mapper.UpdateComponentMapper;
import com.liang.service.UpdateComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateComponentServiceImpl implements UpdateComponentService {

    @Autowired
    private UpdateComponentMapper updateComponentMapper;

    @Override
    public Integer updateComponentStatus(List<Component> componentList) {
        return updateComponentMapper.updateComponentStatus(componentList);
    }

    @Override
    public Integer updateComponent(Component component) {
        // 如果重复，返回-1
        if (updateComponentMapper.getCount(component).equals(1)) {
            return -1;
        }
        // 更新成功返回1
        if (updateComponentMapper.updateComponent(component).equals(1)) {
            return 1;
        }
        return 0;
    }
}
