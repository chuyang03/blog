package com.cy.blog.web.admin;

import com.cy.blog.po.User;
import com.cy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //去到登陆页面
    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){

        User user = userService.checkUser(username, password);

        if (user != null){

            //将user对象放置到session中，防止信息泄漏将用户名密码设置为空
            user.setPassword(null);
            session.setAttribute("user", user);

            return "admin/index";
        }else{

            attributes.addFlashAttribute("message", "用户名或密码错误");

            //重定向，相当于重新发送一个请求。去到登陆页面
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        //注销用户时将session中用户的信息清空
        session.removeAttribute("user");

        return "redirect:/admin";
    }
}
