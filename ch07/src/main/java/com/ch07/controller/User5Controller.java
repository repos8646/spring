package com.ch07.controller;

import com.ch07.dto.User5DTO;
import com.ch07.service.User5Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class User5Controller {

    private final User5Service user5Service;

    @GetMapping("/user5/list")
    public String list(Model model) {

        List<User5DTO> users = user5Service.selectUser5s();
        model.addAttribute("users", users);

        return "/user5/list";
    }

    @GetMapping("/user5/register")
    public String register(){
        return "/user5/register";
    }

    @PostMapping("/user5/register")
    public String register(User5DTO user5DTO){

        user5Service.insertUser5(user5DTO);

        return "redirect:/user5/list";
    }

    @GetMapping("/user5/modify")
    public String modify(int seq, Model model){

        User5DTO user = user5Service.selectUser5(seq);
        model.addAttribute(user);

        return "/user5/modify";
    }

    @PostMapping("/user5/modify")
    public String modify(User5DTO user5DTO){

        user5Service.updateUser5(user5DTO);

        return "redirect:/user5/list";
    }

    @GetMapping("/user5/delete")
    public String delete(int seq){
        user5Service.deleteUser5(seq);
        return "redirect:/user5/list";
    }

}
