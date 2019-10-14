package com.cy.blog.web;

import com.cy.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        //如果有异常出现会自动寻找到templates文件夹下配置的错误页面
//        int i = 9/0;

//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("博客没有找到");
//        }

        System.out.println("------index-------");
        return "index";
    }

    //去到博客详情页面
    @GetMapping("blog")
    public String blog(){
        return "blog";
    }
}
