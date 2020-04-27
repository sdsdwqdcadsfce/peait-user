package com.peait.peaituser.service.impl;

import com.peait.peaituser.entity.MenuList;
import com.peait.peaituser.entity.MenuListVO;
import com.peait.peaituser.exception.GlobalException;
import com.peait.peaituser.mapper.MenuListMapper;
import com.peait.peaituser.result.CodeMsg;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.service.MenuListService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuListServiceImpl implements MenuListService {
    @Resource
    private MenuListMapper menuListMapper;


    /**
     * 获取后台管理系统菜单
     *
     * @param authorization
     * @return
     */
    @Override
    public Result queryEmnuList(String authorization) {
        List<MenuList> dbMenuList = menuListMapper.queryByUserId(authorization);
        if (dbMenuList.isEmpty()) {
            return Result.error(CodeMsg.AUTH_ERROR);
        }
        List<MenuListVO> webMenuList = null;
        try {
            //建立树形图
            webMenuList = this.buildTree(dbMenuList);
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        return Result.success(webMenuList);
    }

    /**
     * 创建菜单树形图
     *
     * @param dbMenuList
     * @return
     */
    private List<MenuListVO> buildTree(List<MenuList> dbMenuList) {
        List<MenuListVO> listResult = new ArrayList<>();
        //获取父节点
        List<MenuListVO> rootNodes = this.getRootNodes(dbMenuList);
        for (MenuListVO rootNode : rootNodes) {
            buildChildNodes(rootNode, dbMenuList);
            listResult.add(rootNode);
        }
        return listResult;
    }

    /**
     * 获取子节点
     *
     * @param rootNode
     * @param dbMenuList
     */
    private void buildChildNodes(MenuListVO rootNode, List<MenuList> dbMenuList) {
        List<MenuListVO> childNodes = getChildNodes(rootNode, dbMenuList);
        if (!childNodes.isEmpty()) {
            for (MenuListVO childNode : childNodes) {
                buildChildNodes(childNode, dbMenuList);
            }
            rootNode.setChildren(childNodes);
        }

    }


    private List<MenuListVO> getChildNodes(MenuListVO rootNode, List<MenuList> dbMenuList) {
        List<MenuListVO> childReuslt = new ArrayList<>();
        for (MenuList me : dbMenuList) {
            if (me.getFId().equals(rootNode.getId())) {
                MenuListVO menuListVO = new MenuListVO();
                BeanUtils.copyProperties(me, menuListVO);
                childReuslt.add(menuListVO);
            }
        }
        return childReuslt;
    }


    /**
     * 获取父节点
     *
     * @param dbMenuList
     * @return
     */
    private List<MenuListVO> getRootNodes(List<MenuList> dbMenuList) {
        List<MenuListVO> rootNodes = new ArrayList<>();
        for (MenuList menu : dbMenuList) {
            if (isRootNodes(menu)) {
                MenuListVO menuList = new MenuListVO();
                BeanUtils.copyProperties(menu, menuList);
                rootNodes.add(menuList);
            }
        }
        return rootNodes;
    }

    /**
     * 判断是不是根节点
     *
     * @param menu
     * @return
     */
    private boolean isRootNodes(MenuList menu) {
        if (menu.getFId().equals("0")) {
            return true;
        } else {
            return false;
        }
    }
}
