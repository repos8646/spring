package com.ch07.controller;

import com.ch07.dto.User3DTO;
import com.ch07.service.User3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class User3Controller {

    private final User3Service user3Service;

    @GetMapping("/user3/list")
    public String list(Model model) {

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
    public String modify(String uid, Model model){

        User3DTO user = user3Service.selectUser3(uid);
        model.addAttribute(user);

        return "/user3/modify";
    }

    @PostMapping("/user3/modify")
    public String modify(User3DTO user3DTO){

        user3Service.updateUser3(user3DTO);

        return "redirect:/user3/list";
    }

    @GetMapping("/user3/delete")
    public String delete(String uid){
        user3Service.deleteUser3(uid);
        return "redirect:/user3/list";
    }

}
