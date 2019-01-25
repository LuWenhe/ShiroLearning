package edu.just;

import edu.just.dao.UserMapper;
import edu.just.model.User;
import edu.just.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void test1() {
        String zhang3 = userService.getPassword("li4");
        System.out.println(zhang3);
    }

    @Test
    public void test() {
        User zhang3 = userMapper.getByName("zhang3");
        System.out.println(zhang3);
    }

}
