package com.ning.controller;
import com.ning.entity.Books;
import com.ning.entity.Readers;
import com.ning.service.ReaderService;
import com.ning.service.impl.BookServiceImpl;
import com.ning.utils.DynamicDataSourceHolder;
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
        //DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        List<Books> bookList = bookService.queryAllBook();
        model.addAttribute("bookList",bookList);
        System.out.println(DynamicDataSourceHolder.getDataSourceType());
        //DynamicDataSourceHolder.clearDataSourceType();
        return "reader/readerAllBook";
    }

    @RequestMapping("/login")
    public String login(String readerId, String password,HttpSession session){
        //
        System.out.println("datasource!!!!-------------------------------");
        DynamicDataSourceHolder.clearDataSourceType();
        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        System.out.println(DynamicDataSourceHolder.getDataSourceType());
        //
        System.out.println("readerId:"+ readerId+" password:"+password+"  ==>login");
        Readers reader = readerService.queryReaderByReaderId(readerId);
        //
        //DynamicDataSourceHolder.clearDataSourceType();
        System.out.println(DynamicDataSourceHolder.getDataSourceType());
        //
        if(reader != null){
            System.out.println(reader);
            if(reader.getCredential().getPassword().equals(password)){
                System.out.println("???????????????"+password);
                //session.setAttribute("msg","????????????");
                session.setAttribute("readerName",reader.getName());
                session.setAttribute("reader",reader);
                return "redirect:/reader/info";
            }else {
                session.setAttribute("msg","???????????? ");
                System.out.println("???????????????"+password);
                return "reader/readerLogin";
            }
        }else {
            session.setAttribute("msg","???????????????");
            System.out.println("??????????????????"+readerId);
            return "reader/readerLogin";
        }
    }

    @RequestMapping("/info")
    public String info(HttpSession session){
        //????????????????????????????????????????????????
        if(session.getAttribute("reader") == null){
            return "redirect:/reader/toLogin";
        }
        return "reader/readerInfo";
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword(HttpSession session){
//        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        session.removeAttribute("msg");
        if(session.getAttribute("reader") == null){
            DynamicDataSourceHolder.clearDataSourceType();
            return "redirect:/reader/toLogin";
        }
//        DynamicDataSourceHolder.clearDataSourceType();
        System.out.println(DynamicDataSourceHolder.getDataSourceType());
        return "reader/changePassword";
    }
    @RequestMapping("/changePassword")
    public String changePassword(String originalPassword,String newPassword,String newPasswordConfirm,HttpSession session){
//        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        session.removeAttribute("msg");
        if(!newPassword.equals(newPasswordConfirm)){
            session.setAttribute("msg","????????????????????????????????????");
//            DynamicDataSourceHolder.clearDataSourceType();
            return "redirect:/reader/toChangePassword";
        }
        Readers reader = (Readers) session.getAttribute("reader");
        if(reader.getCredential().getPassword().equals(originalPassword)){
            System.out.println("????????????--====---->??????"+reader.getCredential().getPassword());
            reader.getCredential().setPassword(newPassword);
            readerService.updateCredentials(reader.getCredential());
            session.setAttribute("reader",reader);  //??????
            System.out.println("????????????--====---->??????"+reader.getCredential().getPassword());
//            DynamicDataSourceHolder.clearDataSourceType();
            return "reader/readerMain";
        }else{
            session.setAttribute("msg","??????????????????");
            System.out.println("??????????????????------???????????????");
//            DynamicDataSourceHolder.clearDataSourceType();
            return "redirect:/reader/toChangePassword";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
//        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        session.removeAttribute("reader");
//        DynamicDataSourceHolder.clearDataSourceType();
        session.removeAttribute("msg");
        return "reader/readerMain";
    }
    @RequestMapping("/aboutUs")
    public String aboutUs(HttpSession session){
        //session.removeAttribute("reader");
        return "reader/aboutUs";
    }


    @RequestMapping("/queryBook")
    public String queryBookName(String bookName,Model model){
//        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        System.out.println("queryBookName=>"+bookName);
        List<Books>list = bookService.queryBookByName(bookName);
        if(!list.isEmpty()){
            model.addAttribute("bookList",list);
//            DynamicDataSourceHolder.clearDataSourceType();
            return "reader/readerAllBook";
        }
        //??????????????????????????????????????????
        list = bookService.queryBookByName("%"+bookName+"%");
        model.addAttribute("bookList",list);
//        DynamicDataSourceHolder.clearDataSourceType();
        return "reader/readerAllBook";
    }

    @RequestMapping("/changePhoneNum")
    public String changePhoneNum(int phoneNum,HttpSession session){
//        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_USER);
        Readers reader = (Readers) session.getAttribute("reader");
        ((Readers) session.getAttribute("reader")).setPhoneNum(phoneNum);
        reader.setPhoneNum(phoneNum);
        readerService.updateReader(reader);
//        DynamicDataSourceHolder.clearDataSourceType();
        return "reader/readerInfo";
    }

}
