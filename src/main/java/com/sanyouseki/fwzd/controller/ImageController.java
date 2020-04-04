package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.Comment;
import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.impl.CommentServiceImpl;
import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.util.MyPageUtil;
import com.sanyouseki.fwzd.util.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private int DEFAULT_LIMIT_COUNT = 20;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private CommentServiceImpl commentService;

    //由于controller的前缀image和文件路径重复了，这里得多打一个“/”才能正确分别……（不能偷懒啊
    @RequestMapping(value = {"/{id}", "/{id}/{currentPage}/{limitCount}/"})
    public ModelAndView image(HttpServletRequest request,
                              @PathVariable(value = "id") Integer id,
                              @PathVariable(value = "currentPage", required = false) Integer currentPage,
                              @PathVariable(value = "limitCount", required = false) Integer limitCount) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            List<Comment> allComment = commentService.getCommentByImageId(id);
            List<Comment> commentList = new ArrayList<>();
            MyPageUtil myPageUtil;
            if (currentPage == null || limitCount == null) {
                myPageUtil = new MyPageUtil(allComment.size(), DEFAULT_LIMIT_COUNT);
            } else {
                myPageUtil = new MyPageUtil(allComment.size(), currentPage, limitCount);
            }
            for (int i : myPageUtil.getItemIndex()) {
                commentList.add(allComment.get(i));
            }
            Image image = imageService.getImageById(id);
            ModelAndView mv = new ModelAndView("image_imformation");
            mv.addObject("image", image);
            mv.addObject("username", mySessionUtil.getSessionUsername());
            mv.addObject("commentList", commentList);
            mv.addObject("pageCount", myPageUtil.getItemCount());
            mv.addObject("currentPage", myPageUtil.getCurrentPage());
            mv.addObject("limitCount", myPageUtil.getLimitCount());
            return mv;
        } else {
            return new ModelAndView("login");
        }
    }

    @RequestMapping(value = "/{id}/comment")
    public String imageComment(HttpServletRequest request,
                               @PathVariable(value = "id") Integer imageId,
                               String comment) {
        try {
            Comment entity = new Comment();
            entity.setImageId(imageId);
            MySessionUtil mySessionUtil = new MySessionUtil(request);
            entity.setUser(mySessionUtil.getSessionUsername());
            entity.setComment(comment);
            commentService.addComment(entity);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editImage(HttpServletRequest request,
                                  @PathVariable(value = "id") Integer id) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            Image image = imageService.getImageById(id);
            ModelAndView mv = new ModelAndView("image_edit");
            mv.addObject("image", image);
            return mv;
        } else {
            return new ModelAndView("login");
        }
    }

    @PostMapping(value = "/edit/submit")
    public String editSubmit(Integer id, String name, String rank) {
        try {
            Image image = imageService.getImageById(id);
            image.setName(name);
            image.setRank(rank);
            imageService.updateImage(image);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
