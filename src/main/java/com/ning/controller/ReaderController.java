package com.ning.controller;

import com.ning.entity.Readers;
import com.ning.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    @Qualifier("ReaderServiceImpl")
    private ReaderService readerService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "reader/readerLogin";
    }

    @RequestMapping("/allBook")
    public String toReaderAllBook(){
        return "reader/readerAllBook";
    }

    @RequestMapping("/nav")//测试
    public String toNav(){
        return "/static/common/navi.jsp";
    }

    @RequestMapping("/login")
    public String login(String readerId, String password,HttpSession session){
        System.out.println("readerId:"+ readerId+" password:"+password+"  ==>login");
        Readers reader = readerService.queryReaderByReaderId(readerId);
        if(reader != null){
            System.out.println(reader);
            if(reader.getCredential().getPassword().equals(password)){
                System.out.println("密码正确："+password);
                //session.setAttribute("msg","密码正确");
                session.setAttribute("readerName",reader.getName());
                session.setAttribute("reader",reader);
                return "reader/readerMain";
            }else {
                session.setAttribute("msg","密码错误");
                System.out.println("密码错误："+password);
                return "reader/readerLogin";
            }
        }else {
            session.setAttribute("msg","用户不存在");
            System.out.println("用户不存在："+readerId);
            return "reader/readerLogin";
        }
    }

    @RequestMapping("/info")
    public String info(HttpSession session){
        //如果没有登录，则重定向到登录界面
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        return "reader/readerInfo";
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword(HttpSession session){
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        return "reader/changePassword";
    }
    @RequestMapping("/changePassword")
    public String changePassword(String originalPassword,String newPassword,HttpSession session){
        session.removeAttribute("msg");
        Readers reader = (Readers) session.getAttribute("reader");
        if(reader.getCredential().getPassword().equals(originalPassword)){
            System.out.println("密码修改--====---->原："+reader.getCredential().getPassword());
            reader.getCredential().setPassword(newPassword);
            readerService.updateCredentials(reader.getCredential());
            session.setAttribute("reader",reader);  //覆盖
            System.out.println("密码修改--====---->后："+reader.getCredential().getPassword());
            return "reader/readerMain";
        }else{
            session.setAttribute("msg","原始密码错误");
            System.out.println("密码修改失败------密码错误！");
            return "redirect:/reader/toChangePassword";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("reader");
        return "reader/readerMain";
    }
}
