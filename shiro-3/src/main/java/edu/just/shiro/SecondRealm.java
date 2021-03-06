package edu.just.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("second realm");

        // 1.把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 2.从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();
        // 假设获取的密码是 123456
        String password;

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
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06--";
        } else if (username.equals("user")) {
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718--";
        }

        // realmName: 当前 realm 对象的 name, 调用父类的 getName() 方法即可
        String realmName = getName();

        // credentialsSalt: 盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

//      SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);

        SimpleAuthenticationInfo info
                = new SimpleAuthenticationInfo("luwenhe", credentials, credentialsSalt, realmName);
        return info;
    }

}
