package edu.just.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.Date;

public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod() {
        System.out.println("testMethod, time: " + new Date().getTime());
    }

}
