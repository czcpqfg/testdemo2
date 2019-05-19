package com.erp.controller;

import com.erp.bean.UploadInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 13:10
 */

@Controller
public class MainController {

    @RequestMapping("home")
    public String toHome() {
        System.out.println(111);
        return "home";
    }

    @RequestMapping("pic/upload")
    public @ResponseBody
    UploadInfo uploadImg(String dir, @RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest request) {
        System.out.println(dir);
        String pathdir = null;
        if ("image".equals(dir)){
            pathdir="pic/";
        }else if ("flash".equals(dir)){
            pathdir="flash/";
        }else if ("media".equals(dir)){
            pathdir="media/";
        }else{
            pathdir = "others/";
        }
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (contentType.contains("jpeg") || contentType.contains("jpg")
                || contentType.contains("png") || contentType.contains("gif") || contentType.contains("video") ||contentType.contains("application/octet-stream")) {
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/"+pathdir);
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            long time = new Date().getTime();
            String imgname = prefix + time + suffix;
            File imgfile = new File(path + imgname);
            if (!imgfile.getParentFile().exists()) {
                imgfile.getParentFile().mkdir();
            }
            try {
                file.transferTo(imgfile);
                return new UploadInfo(0, pathdir + imgname);
            } catch (IOException e) {
                e.printStackTrace();
                return new UploadInfo(1, null);
            }

        }else{
            //未知格式
            return new UploadInfo(1, null);
        }

    }

}
