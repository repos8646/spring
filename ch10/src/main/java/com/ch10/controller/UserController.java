package com.ch10.controller;

import com.ch10.dto.UserDTO;
import com.ch10.entity.User;
import com.ch10.jwt.JwtProvider;
import com.ch10.security.MyUserDetails;
import com.ch10.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider; // jwtProvider쪽에 @Conponent 선언해놔서 주입해서 사용 가능
    private final UserService userService;


    // 인증된 사용자(토큰을 가지고 있는)만 요청할 수 있는 메서드
    @GetMapping("/user")
    public ResponseEntity user(Authentication authentication) {

        log.info("user1 - " + authentication);
        User user = (User) authentication.getPrincipal();
        //MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        //User user = myUserDetails.getUser();

        UserDTO userDTO = userService.selectUser(user.getUid());
        userDTO.setPass(null); // 비밀번호는 보내지 않도록 설정
        log.info("user2 - " + user);

        return ResponseEntity
                .ok()
                .body(userDTO);

    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO){
       log.info("login1 - " + userDTO);

        try {
           // Spring Security 인증 처리
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());

            Authentication authentication = authenticationManager.authenticate(authToken);
            log.info("login2 - " + authentication);

            // 인증된 사용자 객체 가져오기

            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            log.info("login3 - " + myUserDetails);

            User user = myUserDetails.getUser();
            log.info("login4 - " + user);

            // 토큰 생성
            String accessToken = jwtProvider.createToken(user, 1); // 1일
            String refreshToken = jwtProvider.createToken(user, 7); // 7일
            log.info("login5 - " + accessToken);

            // Refresh 토큰 DB 저장

            // Access, Refresh 토큰 전송
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("accessToken", accessToken);
            resultMap.put("refreshToken", refreshToken);

            return ResponseEntity
                    .ok()
                    .body(resultMap);


        }catch (Exception e){
            log.info("login6 - catch!!!");
            // 인증 실패
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("user not found");
        }
    }

}
