package com.ning.controller;
import com.ning.entity.Books;
import com.ning.entity.Readers;
import com.ning.service.ReaderService;
import com.ning.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {


    @Autowired
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("ReaderServiceImpl")
    private ReaderService readerService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "reader/readerLogin";
    }

    @RequestMapping("/allBook")
    public String toReaderAllBook(Model model){
        List<Books> bookList = bookService.queryAllBook();
        model.addAttribute("bookList",bookList);
        return "reader/readerAllBook";
    }

    @RequestMapping("/login")
    public String login(String readerId, String password,HttpSession session){
        System.out.println("readerId:"+ readerId+" password:"+password+"  ==>login");
        Readers reader = readerService.queryReaderByReaderId(readerId);
        if(reader != null){
            System.out.println(reader);
            if(reader.getCredential().getPassword().equals(password)){
                System.out.println("密码正确："+password);
                //session.setAttribute("msg","密码正确");
                session.setAttribute("readerName",reader.getName());
                session.setAttribute("reader",reader);
                return "reader/readerMain";
            }else {
                session.setAttribute("msg","密码错误 ");
                System.out.println("密码错误："+password);
                return "reader/readerLogin";
            }
        }else {
            session.setAttribute("msg","用户不存在");
            System.out.println("用户不存在："+readerId);
            return "reader/readerLogin";
        }
    }

    @RequestMapping("/info")
    public String info(HttpSession session){
        //如果没有登录，则重定向到登录界面
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        return "reader/readerInfo";
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword(HttpSession session){
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        return "reader/changePassword";
    }
    @RequestMapping("/changePassword")
    public String changePassword(String originalPassword,String newPassword,String newPasswordConfirm,HttpSession session){
        session.removeAttribute("msg");
        if(!newPassword.equals(newPasswordConfirm)){
            session.setAttribute("msg","新密码与确认密码不一致！");
            return "redirect:/reader/toChangePassword";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        if(reader.getCredential().getPassword().equals(originalPassword)){
            System.out.println("密码修改--====---->原："+reader.getCredential().getPassword());
            reader.getCredential().setPassword(newPassword);
            readerService.updateCredentials(reader.getCredential());
            session.setAttribute("reader",reader);  //覆盖
            System.out.println("密码修改--====---->后："+reader.getCredential().getPassword());
            return "reader/readerMain";
        }else{
            session.setAttribute("msg","原始密码错误");
            System.out.println("密码修改失败------密码错误！");
            return "redirect:/reader/toChangePassword";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("reader");
        return "reader/readerMain";
    }
    @RequestMapping("/aboutUs")
    public String aboutUs(HttpSession session){
        //session.removeAttribute("reader");
        return "reader/aboutUs";
    }


    @RequestMapping("/queryBook")
    public String queryBookName(String bookName,Model model){
        System.out.println("queryBookName=>"+bookName);
        //模糊查询
        List<Books> list = bookService.queryBookByName("%"+bookName+"%");
        model.addAttribute("bookList",list);
        return "reader/readerAllBook";
    }

}
