package edu.just.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    // 用户认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("first realm");

        // 1.把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 2.从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();
        // 假设获取的密码是 123456
        String password = "fc1709d0a95a6be30bc5926fdb7f22f4";

        // 3.调用数据库的方法，从数据库中查询 username 对应的用户记录
        System.out.println("username: " + username);

        // 4.若用户不存在，则可以抛出 UnknownAccountException 异常
        if (username.equals("unknown")) {
            throw new UnknownAccountException("用户不存在!");
        }

        // 5.根据用户信息的情况，决定是否需要抛出其他的 AuthenticationException 异常
        if (username.equals("monster")) {
            throw new LockedAccountException("用户被锁定");
        }

        // 6.根据用户的情况，来构建 AuthenticationInfo 对象并且返回，
        // 通常使用的实现类是 SimpleAuthenticationInfo
        // 以下信息是从数据库中进行获取的
        // principal: 认证的实体信息, 可以是 username, 也可以是数据库对应的用户的实体类对象
        String principal = username;

        // credentials: 从数据库中获取的密码
        Object credentials = null;
        if (username.equals("admin")) {
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        } else if (username.equals("user")) {
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }

        // realmName: 当前 realm 对象的 name, 调用父类的 getName() 方法即可
        String realmName = getName();

        // credentialsSalt: 盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

//      SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);

        SimpleAuthenticationInfo info
                = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    // 用户授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1.从 PrincipalCollection 中来获取登陆的用户的信息
        Object primaryPrincipal = principals.getPrimaryPrincipal();

        // 2.利用登陆的用户的信息获取当前用户的角色或者权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        roles.add("user");
        // 如果是 admin 用户的话, 则为 admin 用户添加 admin 的角色或者权限
        if (primaryPrincipal.equals("admin")) {
            roles.add("admin");
        }

        // 3.创建 SimpleAuthorizationInfo, 并设置其 roles 角色属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        // 4.返回 SimpleAuthorizationInfo 对象
        return info;
    }
}
