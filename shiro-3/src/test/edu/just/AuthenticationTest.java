package edu.just;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("luwenhe", "123456");
    }

    @Test
    public void testAuthenticationTest() {
        // 1.构建SecurityManager的环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);   // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject();   // 获取当前主体


        UsernamePasswordToken token = new UsernamePasswordToken("luwenhe", "123456");
        subject.login(token);   // 登陆

        // isAuthenticated() 方法用于判断用户是否认证成功
        System.out.println(subject.isAuthenticated());

        subject.logout();

        System.out.println(subject.isAuthenticated());
    }
}
