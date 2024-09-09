package com.ch07.dto;

import com.ch07.entity.User4;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User4DTO {

    private String uid;
    private String name;
    private String gender;
    private int age;
    private String hp;
    private String addr;

    // 엔티티 변환 메서드
    public User4 toEntity(){
        // Builder 방식 생성
        return User4.builder()
                .uid(uid)
                .name(name)
                .gender(gender)
                .age(age)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
