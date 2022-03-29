package com.ning.controller;


import com.ning.entity.Readers;
import com.ning.entity.Record;
import com.ning.service.ReaderService;
import com.ning.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    @Qualifier("RecordServiceImpl")
    private RecordService recordService;

    @RequestMapping("/toRecord")
    public String toRecord(Model model,HttpSession session){
        if(session.getAttribute("reader") == null){
            return "reader/readerMain";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        List<Record> records = recordService.queryRecordByReaderId(reader.getReaderId());
        //session.setAttribute("records",records);
        model.addAttribute("records",records);
        return "reader/readerRecord";
    }
//做完了entity+个人借阅记录demo
}
