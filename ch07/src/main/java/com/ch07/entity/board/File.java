package com.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "board_file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    private String oName;
    private String sName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ano")
    private Article article;
}
