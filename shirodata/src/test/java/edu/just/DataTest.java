package edu.just;

import edu.just.dao.DAO;
import edu.just.dao.JDBCUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class DataTest {

    @Test
    public void test() {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() {
        DAO dao = new DAO();
        String zhang3 = dao.getPassword("zhang3");
        System.out.println(zhang3);
    }

    @Test
    public void test3() {
        DAO dao = new DAO();
        Set<String> set = dao.listRoles("li4");
        System.out.println(set);
    }

    @Test
    public void test4() {
        DAO dao = new DAO();
        Set<String> set = dao.listPermissions("zhang3");
        System.out.println(set);
    }

    @Test
    public void test5() {
        Set<String> set = new DAO().listRoles("zhang3");
        Set<String> set1 = new DAO().listRoles("li4");
        System.out.println("用户zhang3的角色: " + set);
        System.out.println(set + "的权限是: " + new DAO().listPermissions("zhang3"));
        System.out.println("用户li4的角色: " + set1);
        System.out.println(set1 + "的权限: " + new DAO().listPermissions("li4"));
    }

}
