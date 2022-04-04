package com.ning.controller;


import com.ning.entity.Readers;
import com.ning.entity.Staff;
import com.ning.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    @Qualifier("StaffServiceImpl")
    StaffService staffService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "admin/adminLogin";
    }


    @RequestMapping("/login")
    public String login(String jobId, String password, HttpSession session){
        System.out.println("jobId:"+ jobId+" password:"+password+"  ==>login");
        Staff staff = staffService.queryStaffByJobId(jobId);
        if(staff != null){
            System.out.println(staff);
            if(staff.getPassword().equals(password)){
                System.out.println("密码正确："+password);
                //session.setAttribute("msg","密码正确");
                //session.setAttribute("jobId",staff.getName());
                session.setAttribute("staff",staff);
                return "redirect:/book/allBook";
            }else {
                session.setAttribute("msg","密码错误");
                System.out.println("密码错误："+password);
                return "admin/adminLogin";
            }
        }else {
            session.setAttribute("msg","用户不存在");
            System.out.println("用户不存在jobId："+jobId);
            return "admin/adminLogin";
        }
    }
}
