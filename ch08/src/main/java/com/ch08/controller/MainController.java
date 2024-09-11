package com.ch08.controller;

import com.ch08.entity.User;
import com.ch08.security.MyUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Log4j2
@Controller
public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {

        // 로그인 사용자 인증객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication);

        /*
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();

        User user = null;

        if(myUserDetails != null) {
            user = myUserDetails.getUser();
        }
        */
        //model.addAttribute("user", user);

        return "index";
    }

    @GetMapping(value = {"/admin", "/admin/index"})
    public String admin(){
        return "/admin/index";
    }

    @GetMapping(value = {"/manager", "/manager/index"})
    public String manager(){
        return "/manager/index";
    }

    @GetMapping(value = {"/staff", "/staff/index"})
    public String staff(){
        return "/staff/index";
    }
}
