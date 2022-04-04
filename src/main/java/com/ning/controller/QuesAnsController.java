package com.ning.controller;

import com.ning.entity.BookCollect;
import com.ning.entity.QuesAns;
import com.ning.entity.Readers;
import com.ning.service.QuesAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/quesAns")
public class QuesAnsController {
    @Autowired
    @Qualifier("QuesAnsServiceImpl")
    QuesAnsService quesAnsService;

    @RequestMapping("/toQuesAns")
    public String toQuesAns(Model model, HttpSession session){
        Readers reader = (Readers) session.getAttribute("reader");
        if(reader == null){
            return "reader/readerMain";
        }
        List<QuesAns> quesAns = quesAnsService.queryQuesByReaderId(reader.getReaderId());
        model.addAttribute(quesAns);
        return "reader/readerQuesAns";
    }

}
