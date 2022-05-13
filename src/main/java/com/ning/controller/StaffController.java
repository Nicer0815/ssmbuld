package com.ning.controller;


import com.ning.dao.RecordMapper;
import com.ning.entity.QuesAns;
import com.ning.entity.Readers;
import com.ning.entity.Record;
import com.ning.entity.Staff;
import com.ning.service.QuesAnsService;
import com.ning.service.RecordService;
import com.ning.service.StaffService;
import com.ning.utils.DateUtils;
import com.ning.utils.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    @RequestMapping("/return")
    public String returnBook(String readerId,String borrowDate,String bookID){
        Record record = new Record();

        record.setBookId(bookID);
        record.setReaderId(readerId);
        record.setBorrowDate(DateUtils.cstStringToDate(borrowDate));
        record.setReturnDate(new Date());

        int cnt = recordService.staffReturnBook(record);
        System.out.println(""+cnt+"行还书受到影响");
        return "redirect:/staff/toReturn";
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
        List<QuesAns> solvedQuesAns =  quesAnsService.querySolvedQues();
        List<QuesAns> UnsolvedQuesAns =  quesAnsService.queryUnsolvedQues();
        model.addAttribute("solvedQuesAns",solvedQuesAns);
        model.addAttribute("unSolvedQuesAns",UnsolvedQuesAns);
        return "admin/adminQuesAns";
    }
    @RequestMapping("/deleteQuesAns")
    public String deleteQuesAns(String readerId,String askDate){
        QuesAns quesAns = new QuesAns();
        quesAns.setAskDate(DateUtils.cstStringToDate(askDate));
        quesAns.setReaderId(readerId);
        quesAnsService.dropQues(quesAns);
        return "redirect:/staff/toQuesAns";
    }

    @RequestMapping("/quesAnsReply")
    public String quesAnsReply(String readerId,String askDate,String answer,HttpSession session){
        System.out.println("info:"+readerId+askDate+answer);
        QuesAns quesAns = new QuesAns();
        quesAns.setReaderId(readerId);
        quesAns.setAskDate(DateUtils.cstStringToDate(askDate));
        quesAns.setAnswer(answer);
        Staff staff = (Staff) session.getAttribute("staff");
        quesAns.setJobId(staff.getJobId());
        quesAnsService.quesAnsReply(quesAns);
        return "redirect:/staff/toQuesAns";
    }


}
