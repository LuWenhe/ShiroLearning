package edu.just.controller;

import edu.just.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/testShiroAnnotion")
    public String testShiroAnnotion() {
        shiroService.testMethod();
        return "list";
    }

    @RequestMapping("login")
    public String hello(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            token.setRememberMe(false);

            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登陆失败：" + e.getMessage());
            }
        }

        return "list";
    }

}
