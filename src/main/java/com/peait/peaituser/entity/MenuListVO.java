package com.peait.peaituser.entity;

import lombok.Data;

import java.util.List;

@Data
public class MenuListVO {
    private String id;

    private String menuCode;

    private String menuName;

    private String fId;

    private String emnuPath;

    private String userId;

    private Integer menuOrder;

    private Integer menuIndex;

    private List<MenuListVO> children;
}