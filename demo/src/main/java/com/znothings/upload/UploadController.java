package com.znothings.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 上传控制器
 * @author zenghh, 625111833@qq.com
 * @date 2019-06-13 14:40
 * @version 1.0.0
 */
@Controller
@Slf4j
public class UploadController {
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file){
        if (file.isEmpty()){
            return "上传失败，请选择文件";
        }
        String filename = file.getOriginalFilename();
        String filepath="/upload/temp/";
        File dest =new File(filepath+filename);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        }catch (IOException e){
            log.error(e.toString(),e);
        }
        return "上传失败";
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
