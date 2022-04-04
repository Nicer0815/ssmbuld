package com.ning.controller;

import com.ning.entity.Books;
import com.ning.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //Controller 调用 Service层
    @Autowired  //找了一下午的bug
    @Qualifier("BookServiceImpl")
    private BookService bookService;



    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){
        System.out.println("list");
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "admin/adminAllBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "admin/adminAddBook";
    }

    //添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("addBook=>"+books);
        bookService.addBook(books);
        return "redirect:/book/allBook";    //重定向到 @RequestMapping("/allBook")请求
    }

    //跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdatePaper(String id, Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("books",books);
        return "admin/adminUpdateBook";
    }
    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("updateBook=>"+books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }
    //删除书籍
    @RequestMapping("/deleteBook")
    public String deleteBook(int id){
        System.out.println("delete ID=>"+id);
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("/queryBook")
    public String queryBookName(String bookName,Model model){
        System.out.println("queryBookName=>"+bookName);
        //模糊查询
        List<Books> list = bookService.queryBookByName("%"+bookName+"%");
        model.addAttribute("list",list);
        return "admin/adminAllBook";
    }

}
