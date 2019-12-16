package com.liang.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterForm {
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String category = "";
    @Builder.Default
    private String status = "";
}
