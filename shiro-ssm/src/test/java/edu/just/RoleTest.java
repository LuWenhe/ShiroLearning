package edu.just;

import edu.just.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RoleTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testRoleService() {
        Set<String> set = roleService.listRoles("zhang3");
        System.out.println(set);
    }

    @Test
    public void testRoleService2() {
        Set<String> set = roleService.listRoles("li4");
        System.out.println(set);
    }

}
