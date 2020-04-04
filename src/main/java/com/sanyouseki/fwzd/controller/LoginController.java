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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = {"/login", ""})
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MySessionUtil mySessionUtil = new MySessionUtil(request);
        if (!mySessionUtil.isSessionUsernameEmpty()) {
            response.sendRedirect("/fwzd/");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/submit")
    public String loginSubmit(HttpServletRequest request, String username, String password) {
        User user = userService.getUserByName(username);
        if (user == null) {
            return "null";
        }
        if (user.getPassword().equals(password)) {
            MySessionUtil mySessionUtil = new MySessionUtil(request);
            if (!mySessionUtil.getSessionUsername().equals(username)) {
                mySessionUtil.setSessionUsername(username);
            }
            return "success";
        } else {
            return "failed";
        }

    }
}
