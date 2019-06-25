package com.znothings.upload;

import com.znothings.constant.Capacity;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.DateUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * 上传控制器
 * @author zenghh, 625111833@qq.com
 * @date 2019-06-13 14:40
 * @version 1.0.0
 */
@Controller
@Slf4j
public class UploadController {
    @Autowired
    UserDao userDao;
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }
    @GetMapping("/upload2DB")
    public String upload2DB(){
        return "upload2DB";
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(String name,Integer age,MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return "上传失败，请选择文件";
        }
        //目标路径/upload/myproject/
        String filename = LocalDate.now().toString()+file.getOriginalFilename();
        String filepath="/upload/myproject/";
        File dest =new File(filepath+filename);
        if (!dest.exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest.getAbsoluteFile());
            log.info("文件："+filename+"，上传成功到:"+dest.getAbsoluteFile());
            return "上传成功";
        }catch (IOException e){
            log.error(e.toString(),e);
        }
        return "上传失败";
    }

    @PostMapping("upload2DB")
    @ResponseBody
    public String upload2DB(String name,Integer age,MultipartFile file) {
        if (file.isEmpty()){
            return "上传失败，请选择文件";
        }
        //目标路径/upload/myproject/
        String filename = file.getOriginalFilename();
        User user = new User();
        user.setUsername(name);
        user.setAge(age);
        try {
            user.setPhoto(file.getBytes());
            userDao.save(user);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
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

    @GetMapping("getImage")
    @ResponseBody
    public void getImage(Integer id, HttpServletResponse response){
        User user = new User();
        user.setId(id);
        user = userDao.getOne(id);
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Type","image/jpeg;charset=utf-8");
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(user.getPhoto());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
