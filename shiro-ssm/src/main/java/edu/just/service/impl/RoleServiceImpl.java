package edu.just.service.impl;

import edu.just.dao.RoleMapper;
import edu.just.model.Role;
import edu.just.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> listRoles(String userName) {
        List<Role> roles = roleMapper.listRolesByUserName(userName);
        Set<String> set = new HashSet<>();

        for (Role role : roles) {
            set.add(role.getName());
        }
        return set;
    }

}
