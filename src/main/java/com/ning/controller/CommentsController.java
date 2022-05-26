package com.ning.controller;

import com.ning.entity.Comments;
import com.ning.entity.Readers;
import com.ning.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {
    @Autowired  //找了一下午的bug
    @Qualifier("CommentsServiceImpl")
    CommentsService commentsService;
    @RequestMapping("/comment")
    public String Comments(String bookId,String content, HttpSession session){
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        Comments comment = new Comments();
        comment.setReaderId(reader.getReaderId());
        comment.setBookId(bookId);
        comment.setContent(content);
        commentsService.addComment(comment);
        return "redirect:/reader/allBook";
    }


}
