package edu.just.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Date;

public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod() {
        System.out.println("testMethod, time: " + new Date().getTime());

        // 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        // 从 subject 对象中获取 Controller 层的 Session 对象
        Session session = subject.getSession();
        Object key = session.getAttribute("key");

        System.out.println("Service val: " + key);
    }

}
