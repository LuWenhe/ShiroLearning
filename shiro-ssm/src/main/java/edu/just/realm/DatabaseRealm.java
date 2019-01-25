package edu.just.realm;

import edu.just.service.PermissionService;
import edu.just.service.RoleService;
import edu.just.service.UserSerive;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
    private UserSerive userSerive;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取账号密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName= token.getPrincipal().toString();
        String password= new String(token.getPassword());
        //获取数据库中的密码
        String passwordInDB = userSerive.getPassword(userName);

        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
        boolean re = passwordInDB.equals(password);

        if (null == passwordInDB || !re) {
            throw new AuthenticationException();
        }

        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,password,getName());
        return a;
    }

    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入这里，说明账户已经通过验证
        String username = (String) principalCollection.getPrimaryPrincipal();

        // 获取用户的角色
        Set<String> permissions = permissionService.listPermissions(username);
        // 获取用户的权限
        Set<String> roles = roleService.listRoles(username);

        // 授权对象
        SimpleAuthorizationInfo simple = new SimpleAuthorizationInfo();
        simple.setStringPermissions(permissions);
        simple.setRoles(roles);

        return simple;
    }
}
