package com.ch04.controller;

import com.ch04.dto.User2DTO;
import com.ch04.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class User2Controller {

    private User2Service service;

    // 주입
    @Autowired
    public User2Controller(User2Service service) {
        this.service = service;
    }

    @GetMapping("user2/register")
    public String register(){
        return "/user2/register";
    }

    @PostMapping("/user2/register")
    public String register(User2DTO dto){
        System.out.println(dto);

        // 등록
        service.insertUser2(dto);

        // 리다이렉트
        return "redirect:/user2/list";
    }

    @GetMapping("user2/list")
    public String List(Model model){

        // 조회
        List<User2DTO> users = service.selectUser2s();
        System.out.println(users);

        // 모델참조(Controller 데이터를 View에서 참조)
        model.addAttribute("users", users);

        return "/user2/list";
    }

    @GetMapping("user2/modify")
    public String modify(@RequestParam("uid") String uid, Model model){ // @어노테이션 생략 가능
        System.out.println("uid : " + uid);

        // 수정 회원 조회
        User2DTO user = service.selectUser2(uid);

        // 모델참조
        model.addAttribute(user); // 타입명으로 저장(소문자 시작 -> user1DTO)

        return "/user2/modify";
    }

    @PostMapping("/user2/modify")
    public String modify(User2DTO dto){ // @ModelAttribute 생략되어 있음
        System.out.println(dto);

        // 수정
        service.updateUser2(dto);

        // 리다이렉트
        return "redirect:/user2/list";
    }

    @GetMapping("user2/delete")
    public String delete(User2DTO user){ // 어노테이션 생략 안되는 듯, 안 넣으면 에러500 호출됨

        System.out.println("user.getUid() : " + user.getUid());

        // 삭제
        service.deleteUser2(user.getUid());
        
        // 리다이렉트
        return "redirect:/user2/list";
    }
}
