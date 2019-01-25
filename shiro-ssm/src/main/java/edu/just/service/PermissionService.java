package edu.just.service;

import java.util.Set;

public interface PermissionService {

    /**
     * 根据用户名获取用户权限
     */
    Set<String> listPermissions(String userName);

}
