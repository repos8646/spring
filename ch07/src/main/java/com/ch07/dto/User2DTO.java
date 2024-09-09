package com.ch07.dto;

import com.ch07.entity.User1;
import com.ch07.entity.User2;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User2DTO {

    private String uid;
    private String name;
    private String birth;
    private String addr;

    // 엔티티 변환 메서드
    public User2 toEntity(){
        // Builder 방식 생성
        return User2.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .addr(addr)
                .build();
    }
}
