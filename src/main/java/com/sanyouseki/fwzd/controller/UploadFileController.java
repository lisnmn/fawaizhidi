package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.util.MyFileUtil;
import com.sanyouseki.fwzd.util.MySessionUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload_file")
public class UploadFileController {

    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping("/")
    public ModelAndView uploadFile(HttpServletRequest request) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            ModelAndView mv = new ModelAndView("upload_file");
            mv.addObject("username", mySessionUtil.getSessionUsername());
            return mv;
        } else {
            return new ModelAndView("400");
        }
    }

    @PostMapping(value = "/upload")
    public Map<String, Object> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (file.isEmpty()) {
            map.put("msg", "empty");
            return map;
        }

        MySessionUtil mySessionUtil = new MySessionUtil(request);
        MyFileUtil myFileUtil = new MyFileUtil(mySessionUtil.getSessionUsername() + "/" + file.getOriginalFilename());

        try {
            File upload = myFileUtil.getFilePath();
            file.transferTo(upload);
            myFileUtil.createThumb();
            Image image = new Image();
            image.setUploader(mySessionUtil.getSessionUsername());
            image.setName(myFileUtil.getName());
            image.setUrl(myFileUtil.getFileUrl());
            image.setThumbUrl(myFileUtil.getThumbUrl());
            imageService.addImage(image);
            map.put("msg", "上传成功！");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg", "上传失败！");
            return map;
        }
    }

}
