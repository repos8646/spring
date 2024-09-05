package com.ch05.controller;

import com.ch05.dto.User3DTO;
import com.ch05.service.User3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class User3Controller {

    private final User3Service user3Service;

    @Autowired
    public User3Controller(User3Service user3Service) {
        this.user3Service = user3Service;
    }


    @GetMapping("/user3/list")
    public String list(Model model){

        System.out.println("list!!!");

        List<User3DTO> users = user3Service.selectUser3s();

        model.addAttribute("users", users);

        return "/user3/list";
    }

    @GetMapping("/user3/register")
    public String register(){
        return "/user3/register";
    }

    @PostMapping("/user3/register")
    public String register(User3DTO user3DTO){
        user3Service.insertUser3(user3DTO);
        return "redirect:/user3/list";
    }

    @GetMapping("/user3/modify")
    public String modify(@RequestParam("uid") String uid, Model model){
        System.out.println("uid : " + uid);

        User3DTO user = user3Service.selectUser3(uid);
        model.addAttribute(user);

        return "/user3/modify";
    }

    @PostMapping("/user3/modify")
    public String modify(User3DTO dto){

        user3Service.updateUser3(dto);
        return "redirect:/user3/list";
    }

    @GetMapping("/user3/delete")
    public String delete(@RequestParam("uid") String uid){

        user3Service.deleteUser3(uid);

        return "redirect:/user3/list";
    }

}