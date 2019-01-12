package edu.just.shiro.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);

    public static void main(String[] args) {

        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        // 获取当前的 subject，调用 SecurityUtils.getSubject()
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        // 测试使用 Session
        // 获取 Session: 调用 Suject 的 getSession 方法
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("----------------> Retrieved the correct value! [" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        // 测试当前用户是否已经被认证，即是否已经登陆
        // 调用 Subject 的 isAuthenticated 方法，如果没有登陆，则之后该方法之后的代码
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken
            // 这里使用用户名是 lonestarr, 密码是 vespa 的账户进行登陆
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // rememberMe
            token.setRememberMe(true);
            try {
                // 执行登陆
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                // 如果没有指定的账户，shiro 将会抛出 UnknownAccountException 异常
                log.info("-------------------->There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                // 若账户存在，但密码不匹配，则 shiro 会抛出 IncorrectCredentialsException 异常
                log.info("-------------------->Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                // 用户被锁定的异常
                log.info("-------------------->The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("----------------------->User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        // 测试用户是否有某一个角色，调用 Subject 的 hasRole 方法
        if (currentUser.hasRole("schwartz")) {
            log.info("------->May the Schwartz be with you!");
        } else {
            log.info("-------->Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        // 测试用户是否具备某一个行为，调用 Subject 的 isPermitted 方法
        // 这里指的是用户是否可以对 lightsaber 做任何事情，schwartz = lightsaber:*
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("---------> You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("---------> Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        // 测试用户是否具备某一个行为，比上面更加具体
        // 这里指的是用户是否允许对 winnebago 类型的 eagle5 实例做 drive 方法
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("---------->You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        System.out.println("--------->" + currentUser.isAuthenticated());

        //all done - log out!
        // 执行登出, 调用 Subject 的 logout 方法
        currentUser.logout();

        System.out.println("-------->" + currentUser.isAuthenticated());

        System.exit(0);
    }
}
