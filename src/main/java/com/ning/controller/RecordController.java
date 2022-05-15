package com.ning.controller;


import com.ning.entity.Readers;
import com.ning.entity.Record;
import com.ning.service.ReaderService;
import com.ning.service.RecordService;
import com.ning.utils.DateUtils;
import org.junit.FixMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    @Qualifier("RecordServiceImpl")
    private RecordService recordService;

    @RequestMapping("/toRecord")
    public String toRecord(Model model,HttpSession session){
        if(session.getAttribute("reader") == null){
            System.out.println("reader为null------------");
            return "reader/readerMain";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        List<Record> records = recordService.queryRecordByReaderId(reader.getReaderId());
        //session.setAttribute("records",records);
        model.addAttribute("records",records);
        return "reader/readerRecord";
    }

    @RequestMapping("/toReturn")
    public String toReturn(Model model,HttpSession session){
        if(session.getAttribute("reader") == null){
            System.out.println("reader为null------------");
            return "reader/readerMain";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        List<Record> records = recordService.queryUnReturnByReaderId(reader.getReaderId());
        session.setAttribute("records",records);
        model.addAttribute("records",records);
        return "reader/readerReturn";
    }

    @RequestMapping("/toContinue")
    public String toContinue(Model model,HttpSession session){
        if(session.getAttribute("reader") == null){
            System.out.println("reader为null------------");
            return "reader/readerMain";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        List<Record> records = recordService.queryContinueByReaderId(reader.getReaderId());
        session.setAttribute("records",records);
        model.addAttribute("records",records);
        return "reader/readerContinue";
    }

    @RequestMapping("/return")
    public String Return(String bookId,String borrowDate,Model model,HttpSession session){
        Date date = DateUtils.cstStringToDate(borrowDate);
        System.out.println(bookId+"请求归还,借出日期："+borrowDate);
        List<Record> records = (List<Record>) session.getAttribute("records");
        System.out.println(records);
        for(Record record : records){
            if(record.getBookId().equals(bookId) && DateUtils.equals(date,record.getBorrowDate())){
                System.out.println(record);
                record.setState("正在还书");
                record.setState("待还");
                System.out.println(record);
                //recordService.updateRecord(record);
            }
        }
        return "redirect:/record/toReturn";
    }
    @RequestMapping("/continue")
    public String Continue(String bookId, String borrowDate, Model model, HttpSession session){
        //Sun Mar 12 00:00:00 CST 2023
        Date date = DateUtils.cstStringToDate(borrowDate);
        System.out.println(bookId+"请求续约,借出日期："+date);
        List<Record> records = (List<Record>) session.getAttribute("records");
        for(Record record : records){
            if(record.getBookId().equals(bookId) && DateUtils.equals(date,record.getBorrowDate())){
                System.out.println(record);
                System.out.println(bookId+"请求续约,原期限日期："+record.getExpectDate());
                System.out.println("续借15天ing");
                // 续借15天
                Date newExpectDate = DateUtils.plus15Days(record.getExpectDate());
                record.setExpectDate(newExpectDate);
                System.out.println("新的归还期限日期："+record.getExpectDate());
                record.setState("续借");
                System.out.println(record);
                //recordService.updateRecord(record);
            }
        }
        return "redirect:/record/toReturn";
    }
}
