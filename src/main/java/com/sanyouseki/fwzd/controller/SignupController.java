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
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @PostMapping(value = "/submit")
    public String signupSubmit(String username, String password, String answer) {
        if (!answer.equals("ミスティア・ローレライ") && !answer.equals("Mystia Lorelei") && !answer.equals("米斯蒂娅·萝蕾拉") && !answer.equals("小碎骨") && !answer.equals("老板娘")) {
            return "answerFailed";
        }
        if (userService.getUserByName(username) != null) {
            return "failed";
        } else {
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            userService.addUser(user);
            return "success";
        }
    }

}
