package edu.just.dao;

import edu.just.model.User;

public interface UserMapper {

    User getByName(String name);

}
