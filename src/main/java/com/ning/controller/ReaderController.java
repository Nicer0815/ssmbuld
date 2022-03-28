package com.ning.controller;

import com.ning.entity.Readers;
import com.ning.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    @Qualifier("ReaderServiceImpl")
    private ReaderService readerService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        //System.out.println("toLogin");
        return "login";
    }

    @RequestMapping("/allBook")
    public String toReaderAllBook(){
        return "readerAllBook";
    }

    @RequestMapping("/nav")
    public String toNav(){
        return "navi";
    }

    @RequestMapping("/login")
    public String login(String readerId,String password,Model model){
        System.out.println("readerId:"+ readerId+" password:"+password+"  ==>login");
        Readers reader = readerService.queryReaderByReaderId(readerId);
        if(reader != null){
            System.out.println(reader);
            if(reader.getCredential().getPassword().equals(password)){
                System.out.println("密码正确："+password);
                model.addAttribute("msg","密码正确");
                return "main";
            }else {
                model.addAttribute("msg","密码错误");
                System.out.println("密码错误："+password);
                return "login";
            }
        }else {
            model.addAttribute("msg","用户不存在");
            System.out.println("用户不存在："+readerId);
            return "login";
        }
    }
}
