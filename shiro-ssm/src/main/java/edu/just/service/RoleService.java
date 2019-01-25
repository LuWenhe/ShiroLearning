package edu.just.service;

import java.util.Set;

public interface RoleService {

    /**
     * 根据用户获取角色
     */
    Set<String> listRoles(String userName);

}
