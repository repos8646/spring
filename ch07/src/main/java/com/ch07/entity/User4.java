package com.ch07.entity;

import com.ch07.dto.User4DTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

// Entity는 @Setter 안씀
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // 엔티티 객체 정의
@Table(name = "user4")  // 매핑 테이블 설정
public class User4 {

    @Id // PK 컬럼 설정(엔티티에 반드시 선언)
    private String uid;

    @Column(name = "name") // 매핑 컬럼 설정 (생략 가능)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "hp")
    private String hp;

    @Column(name = "addr")
    private String addr;

    // DTO 변환 메서드
    public User4DTO toDTO() {
        return User4DTO.builder()
                .uid(uid)
                .name(name)
                .gender(gender)
                .age(age)
                .hp(hp)
                .addr(addr)
                .build();
    }

}
