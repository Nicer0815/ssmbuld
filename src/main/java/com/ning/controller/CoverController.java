package com.ning.controller;

import com.ning.entity.BooksCover;
import com.ning.service.BooksCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

@Controller
public class CoverController {
    @Autowired  //找了一下午的bug
    @Qualifier("BooksCoverServiceImpl")
    BooksCoverService booksCoverService;

    @RequestMapping("/ImageLoading")
    public void ImageLoading(String bookId,HttpServletRequest request, HttpServletResponse response) {
        InputStream in = null;
        ServletOutputStream oStream = null;
        try {
            response.reset();
            response.setContentType("image/jpeg"); //设置repose返回类型，这个很重要
            request.setCharacterEncoding("utf-8");

            oStream =response.getOutputStream();
            Blob blob;
            //通过java获取到oracle数据库中的blob字段
            BooksCover booksCover = booksCoverService.queryCoverByBookId(bookId);
            blob = (Blob) booksCover.getCover();
            in = blob.getBinaryStream();
            int blobSize = (int) blob.length();//获取blob长度
            byte[] blobBytes = new byte[blobSize];
            int bytesRead = 0;
            //循环写入outputStream
            while ((bytesRead = in.read(blobBytes)) != -1) {
                oStream.write(blobBytes, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oStream != null) {
                try {
                    oStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
