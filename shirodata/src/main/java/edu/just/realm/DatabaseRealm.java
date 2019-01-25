package edu.just.realm;

import edu.just.dao.DAO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 获取从 login 方法传过来的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 用户名
        String username = token.getUsername();
        // 密码（用户输入的密码）
        String password = new String(token.getPassword());
        // 数据库中保存的密码
        String password1 = new DAO().getPassword(username);

        // 如果两个密码不匹配
        if (password1 == null || !password1.equals(password)) {
            throw new AuthenticationException();
        }

        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1.从 PrincipalCollection 中来获取登陆的用户的信息
        String username = (String) principalCollection.getPrimaryPrincipal();

        // 通过 DAO 获取权限
        Set<String> permissions = new DAO().listPermissions(username);
        // 通过 DAO 获取角色
        Set<String> roles = new DAO().listRoles(username);

        // 授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();

        // 把通过 DAO 获取到的角色和权限放进
        s.setStringPermissions(permissions);
        s.setRoles(roles);

        return s;
    }
}
