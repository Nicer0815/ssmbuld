package com.ning.controller;


import com.ning.dao.RecordMapper;
import com.ning.entity.QuesAns;
import com.ning.entity.Readers;
import com.ning.entity.Record;
import com.ning.entity.Staff;
import com.ning.service.QuesAnsService;
import com.ning.service.RecordService;
import com.ning.service.StaffService;
import com.ning.utils.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    @Qualifier("StaffServiceImpl")
    StaffService staffService;

    @Autowired
    @Qualifier("RecordServiceImpl")
    RecordService recordService;

    @Autowired
    @Qualifier("QuesAnsServiceImpl")
    QuesAnsService quesAnsService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "admin/adminLogin";
    }


    @RequestMapping("/login")
    public String login(String jobId, String password, HttpSession session){
        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_DEVELOPER);
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
    //TODO  toReturn toHandle toQuesAns logout

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("staff");
        return "redirect:/staff/toLogin";
    }
    @RequestMapping("/toReturn")
    public String toReturn(Model model){
        List<Record> unReturnRecords = recordService.queryUnReturn();
        model.addAttribute("unReturnRecords",unReturnRecords);
        return "admin/adminReturn";
    }

    //废弃，废除 待归还 这个状态
    @RequestMapping("/toHandle")
    public String toHandle(Model model, HttpSession session){
        //处理
        List<Record> unReturnRecords = recordService.queryUnReturn();
        model.addAttribute("unReturnRecords",unReturnRecords);
        return "admin/adminHandle";
    }
    @RequestMapping("/toQuesAns")
    public String toQuesAns(Model model){
        List<QuesAns> allQuesAns =  quesAnsService.queryAllQues();
        List<QuesAns> UnsolvedQuesAns =  quesAnsService.queryAllQues();
        model.addAttribute("allQuesAns",allQuesAns);
        model.addAttribute("unSolvedQuesAns",UnsolvedQuesAns);
        return "admin/adminQuesAns";
    }
}
