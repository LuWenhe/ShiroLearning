package edu.just.dao;

import edu.just.model.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> listRolesByUserName(String userName);

}
