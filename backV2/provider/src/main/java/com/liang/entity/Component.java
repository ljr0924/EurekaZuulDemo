package com.liang.entity;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Component {

    private Integer id;

    @NotNull(message = "名称不能为空")
    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotNull(message = "分类不能为空")
    @NotEmpty(message = "分类不能为空")
    private String category;

    @NotNull(message = "版本不能为空")
    @NotEmpty(message = "版本不能为空")
    private String version;

    private Integer status;

}
