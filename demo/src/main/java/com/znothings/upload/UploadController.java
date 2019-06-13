package com.znothings.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 上传控制器
 * @author zenghh, 625111833@qq.com
 * @date 2019-06-13 14:40
 * @version 1.0.0
 */
@Controller
public class UploadController {
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file){
        return "";
    }

    @GetMapping("/multiUpload")
    public String multiUpload(){
        return "multiUpload";
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        return "multiUpload";
    }
}
