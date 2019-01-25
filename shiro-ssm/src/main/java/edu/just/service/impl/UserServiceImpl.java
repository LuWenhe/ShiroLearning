package edu.just.service.impl;

import edu.just.dao.UserMapper;
import edu.just.model.User;
import edu.just.service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerive {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getPassword(String name) {
        User u = userMapper.getByName(name);
        return u.getPassword();
    }

}
