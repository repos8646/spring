package com.ch07.entity;

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
@Table(name = "child")  // 매핑 테이블 설정
public class Child {

    @Id
    private String cid;
    private String name;
    private String hp;
    private String parent;
}
