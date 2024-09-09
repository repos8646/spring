package com.ch07.dto;

import com.ch07.entity.User3;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User3DTO {

    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String addr;

    // 엔티티 변환 메서드
    public User3 toEntity(){
        // Builder 방식 생성
        return User3.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
