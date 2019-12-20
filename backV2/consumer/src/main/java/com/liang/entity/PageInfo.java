package com.liang.entity;


import lombok.Data;

import java.util.List;

@Data
public class PageInfo {

    private Integer total;

    private List<Component> list;

    private Integer pageNum;

    private Integer pageSize;

}
