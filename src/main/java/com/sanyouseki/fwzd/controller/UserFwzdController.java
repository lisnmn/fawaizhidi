package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.util.MyFileUtil;
import com.sanyouseki.fwzd.util.MyPageUtil;
import com.sanyouseki.fwzd.util.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userfwzd")
public class UserFwzdController {
    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping(value = {"/", "/{currentPage}/{limitCount}"})
    public ModelAndView homepage(HttpServletRequest request,
                                 @PathVariable(value = "currentPage", required = false) Integer currentPage,
                                 @PathVariable(value = "limitCount", required = false) Integer limitCount) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            List<Image> allImage = imageService.getImageByUploader(mySessionUtil.getSessionUsername());
            List<Image> imageList = new ArrayList<>();
            MyPageUtil myPageUtil;
            if (currentPage == null || limitCount == null) {
                myPageUtil = new MyPageUtil(allImage.size());
            } else {
                myPageUtil = new MyPageUtil(allImage.size(), currentPage, limitCount);
            }
            for (int i : myPageUtil.getItemIndex()) {
                imageList.add(allImage.get(i));
            }
            ModelAndView mv = new ModelAndView("userpage");
            mv.addObject("username", mySessionUtil.getSessionUsername());
            mv.addObject("imageList", imageList);
            mv.addObject("pageCount", myPageUtil.getItemCount());
            mv.addObject("currentPage", myPageUtil.getCurrentPage());
            mv.addObject("limitCount", myPageUtil.getLimitCount());
            return mv;
        } else {
            return new ModelAndView("login");
        }
    }

    @PostMapping(value = "/delete")
    public String delete(HttpServletRequest request, int id) {
        try {
            Image image = imageService.getImageById(id);
            MyFileUtil myFileUtil = new MyFileUtil(image.getUrl());
            myFileUtil.delete();

            imageService.deleteImageById(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
