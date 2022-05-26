package com.ning.controller;


import com.ning.entity.*;
import com.ning.service.*;
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
    @Qualifier("ReaderServiceImpl")
    ReaderService readerService;

    @Autowired
    @Qualifier("BookServiceImpl")
    BookService bookService;

    @Autowired
    @Qualifier("RecordServiceImpl")
    RecordService recordService;

    @Autowired
    @Qualifier("QuesAnsServiceImpl")
    QuesAnsService quesAnsService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpSession session){
        session.removeAttribute("msg");
        return "admin/adminLogin";
    }


    @RequestMapping("/login")
    public String login(String jobId, String password, HttpSession session){
        DynamicDataSourceHolder.clearDataSourceType();
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
        //处理对象： 未还、续借、逾期
        List<Record> unReturnRecords = recordService.queryUnReturn();
        model.addAttribute("unReturnRecords",unReturnRecords);
        return "admin/adminHandle";
    }
    @RequestMapping("/handle")
    public String handle(String readerId,String bookId,String state,String borrowDate,String expectDate){
        Record record = new Record();
        record.setBookId(bookId);
        record.setBorrowDate(DateUtils.cstStringToDate(borrowDate));
        record.setExpectDate(DateUtils.cstStringToDate(expectDate));
        record.setReaderId(readerId);
        record.setState(state);
        System.out.println(record);
        recordService.updateRecord(record);
        //TODO: remainNum -1
        //丢失、损坏、逾期
        return "redirect:/staff/toHandle";
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

    @RequestMapping("/toApprove")
    public String toApprove(){
        return "admin/adminApprove";
    }

    @RequestMapping("/approveBorrow")
    public String approveBorrow(String bookId,String readerId,Model model){
        //业务代码
        Books books = bookService.queryBookById(bookId);
        Readers readers = readerService.queryReaderByReaderId(readerId);
        if (books == null || readers == null){
            model.addAttribute("msg","ISBN号或读者账号有误！");
        }else{
            Record record = new Record();
            Date now = new Date();
            record.setBorrowDate(now);
            record.setExpectDate(DateUtils.plus15Days(now));
            record.setReaderId(readerId);
            record.setBookId(bookId);
            record.setState("待还");
            System.out.println(record);
            recordService.addRecord(record);
            model.addAttribute("msg","借出成功！");
        }
        return "admin/approveResult";
    }

}
