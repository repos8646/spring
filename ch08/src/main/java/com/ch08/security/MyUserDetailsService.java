package com.ch08.security;

import com.ch08.entity.User;
import com.ch08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override // 인증 처리 해주는 메서드 (username - ID)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 사용자가 입력한 아이디로 사용자 조회, 비밀번호에 대한 검증은 이전 컴포넌트인 AuthenticationProvider에서 수행
        Optional<User> optUser = userRepository.findById(username);

        if(optUser.isPresent()) {
            // 시큐리티 사용자 인증 객체 생성 후 리턴
            MyUserDetails myUserDetails = MyUserDetails.builder()
                                                .user(optUser.get())
                                                .build();

            return myUserDetails;
        }
        // 사용자가 입력한 아이디가 없는 경우
        return null;
    }
}
