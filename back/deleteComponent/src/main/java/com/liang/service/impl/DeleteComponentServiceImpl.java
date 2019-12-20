package com.liang.service.impl;

import com.liang.mapper.DeleteComponentMapper;
import com.liang.service.DeleteComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteComponentServiceImpl implements DeleteComponentService {
    @Autowired
    private DeleteComponentMapper componentMapper;

    @Override
    public Integer deleteComponent(List<String> idList) {
        System.out.println(idList);
        return componentMapper.delete(idList);
    }
}
