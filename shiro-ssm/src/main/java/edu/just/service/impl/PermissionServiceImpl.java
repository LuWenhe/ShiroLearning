package edu.just.service.impl;

import edu.just.dao.PermissionMapper;
import edu.just.model.Permission;
import edu.just.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> listPermissions(String userName) {
        List<Permission> permissions = permissionMapper.listPermissionByUserName(userName);
        Set<String> set = new HashSet<>();

        for (Permission permission : permissions) {
            set.add(permission.getName());
        }
        return set;
    }
}
