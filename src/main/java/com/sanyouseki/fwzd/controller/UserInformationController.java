package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.service.impl.UserServiceImpl;
import com.sanyouseki.fwzd.util.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user_information")
public class UserInformationController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public ModelAndView userInformation(HttpServletRequest request) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            User user = userService.getUserByName(mySessionUtil.getSessionUsername());
            ModelAndView mv = new ModelAndView("user_information");
            mv.addObject("user", user);
            return mv;
        } else {
            return new ModelAndView("login");
        }
    }

    @PostMapping(value = "/submit")
    public String userInformationSubmit(HttpServletRequest request, String username, String password) {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        for (User user : userService.getAllUsers()) {
            if (username.equals(user.getName()) && !username.equals(mySessionUtil.getSessionUsername())) {
                return "duplicated";
            }
        }
        User currentUser = userService.getUserByName(mySessionUtil.getSessionUsername());
        currentUser.setName(username);
        currentUser.setPassword(password);
        try {
            userService.updateUser(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "SQLError";
        }
        if (!username.equals(mySessionUtil.getSessionUsername())) {
            mySessionUtil.setSessionUsername(username);
        }
        return "success";
    }

    @RequestMapping("/delete")
    public ModelAndView userInformationDelete(HttpServletRequest request){
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        userService.deleteUserByName(mySessionUtil.getSessionUsername());
        mySessionUtil.getSession().invalidate();
        return new ModelAndView("login");
    }
}
