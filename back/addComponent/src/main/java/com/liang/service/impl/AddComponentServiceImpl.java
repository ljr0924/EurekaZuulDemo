package com.liang.service.impl;

import com.liang.entity.Component;
import com.liang.mapper.AddComponentMapper;
import com.liang.service.AddComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddComponentServiceImpl implements AddComponentService {

    @Autowired
    private AddComponentMapper addComponentMapper;

    @Override
    public Integer save(Component component) {
        if (addComponentMapper.getCount(component).equals(1)) {
            return -1;
        }
        return addComponentMapper.save(component);
    }
}
