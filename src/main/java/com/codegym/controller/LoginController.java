package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public ModelAndView index(@CookieValue(defaultValue = "") String email,
                              @CookieValue(defaultValue = "") String password){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("email", email);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

    @PostMapping("/dologin")
    public ModelAndView doLogin(@ModelAttribute User user,
                                @RequestParam(defaultValue = "") String rememberMe,
                          HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (rememberMe.equals("remember-me") && user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")){
            modelAndView.addObject("message","Login Successed!");
            Cookie saveEmail = new Cookie("email", user.getEmail());
            Cookie savePassword = new Cookie("password", user.getPassword());
            response.addCookie(saveEmail);
            response.addCookie(savePassword);
            return modelAndView;
        } else {
            modelAndView.addObject("message","Login Failed!");
            return modelAndView;
        }

    }
}
