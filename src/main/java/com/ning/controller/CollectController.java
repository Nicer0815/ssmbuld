package com.ning.controller;
import com.ning.entity.BookCollect;
import com.ning.entity.Readers;
import com.ning.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    @Qualifier("CollectServiceImpl")
    private CollectService collectService;

    @RequestMapping("/toCollect")
    public String toCollect(Model model, HttpSession session){
        Readers reader = (Readers) session.getAttribute("reader");
        if(reader == null){
            return "reader/readerMain";
        }
        List<BookCollect> bookCollects = collectService.queryCollectByReaderId(reader.getReaderId());
        model.addAttribute(bookCollects);
        return "reader/readerCollect";
    }

    @RequestMapping("/dropCollect")
    public String dropCollect(String bookId,HttpSession session){
        BookCollect collect = new BookCollect();
        collect.setBookId(bookId);
        Readers reader = (Readers)session.getAttribute("reader");
        collect.setReaderId(reader.getReaderId());
        System.out.println("正在删除收藏："+collect);
        //collectService.deleteCollect(collect);
        return "redirect:/collect/toCollect";
    }

}
