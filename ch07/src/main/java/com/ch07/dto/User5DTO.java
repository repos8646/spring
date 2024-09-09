package com.ch07.dto;

import com.ch07.entity.User5;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User5DTO {

    private int seq;
    private String name;
    private String gender;
    private int age;
    private String addr;

    // 엔티티 변환 메서드
    public User5 toEntity(){
        // Builder 방식 생성
        return User5.builder()
                .seq(seq)
                .name(name)
                .gender(gender)
                .age(age)
                .addr(addr)
                .build();
    }
}
