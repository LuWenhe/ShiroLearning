package edu.just.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/login.jsp", "anon");
        map.put("/login", "anon");
        map.put("/logout", "logout");

        // 用户需要认证(登陆)通过, 同时具有 user 的权限才可以访问 user.jsp 页面
        map.put("/user.jsp", "authc,roles[user]");
        // 用户需要认证(登陆)通过, 同时具有 admin 的权限才可以访问 admin.jsp 页面
        map.put("/admin.jsp", "authc,roles[admin]");

        // 用户通过身份验证/记住我登陆的都可以跳转到 list.jsp
        map.put("/list.jsp", "user");
        map.put("/**", "authc");

        return map;
    }

}
