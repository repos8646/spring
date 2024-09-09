package com.ch07.entity;

import com.ch07.dto.User5DTO;
import jakarta.persistence.*;
import lombok.*;

// Entity는 @Setter 안씀
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // 엔티티 객체 정의
@Table(name = "user5")  // 매핑 테이블 설정
public class User5 {

    @Id // PK 컬럼 설정(엔티티에 반드시 선언)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  해당 필드(컬럼)값이 자동 1 증가(auto_increment)
    private int seq;

    @Column(name = "name") // 매핑 컬럼 설정 (생략 가능)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "addr")
    private String addr;

    // DTO 변환 메서드
    public User5DTO toDTO() {
        return User5DTO.builder()
                .seq(seq)
                .name(name)
                .gender(gender)
                .age(age)
                .addr(addr)
                .build();
    }

}
