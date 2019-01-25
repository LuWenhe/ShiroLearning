package edu.just.dao;

import edu.just.model.Permission;

import java.util.List;

public interface PermissionMapper {

    /**
     * 根据用户名获取权限
     */
    List<Permission> listPermissionByUserName(String userName);

}
