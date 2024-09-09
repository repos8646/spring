package com.ch07.service;

import com.ch07.dto.User4DTO;
import com.ch07.entity.User4;
import com.ch07.repository.User4Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class User4Service {

    // 생성자 주입
    private final User4Repository user4Repository;

    public void insertUser4(User4DTO user4DTO) {

        // DTO를 Entity로 변환
        User4 entity = user4DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        user4Repository.save(entity);
    }

    public User4DTO selectUser4(String uid){

        /*
            Optional
             - null 체크 검정을 손쉽고 안전하게 처리하기 위한 클래스
             - Spring JPA find~ 메서드의 결과 타입
        */
        Optional<User4> opt = user4Repository.findById(uid);

        // Entity 존재 확인 후 Optional 타입으로 정의된 Entity 가져오기
        if(opt.isPresent()){
            User4 user4 = opt.get();

            // Entity를 DTO로 변환해서 반환
            return user4.toDTO();
        }
        return null;
    }

    public List<User4DTO> selectUser4s(){

        // Entity 전체 조회
        List<User4> user4s = user4Repository.findAll();

        // for문(외부 반복자)으로 Entity 리스트를 DTO 리스트로 변환
        /*List<User4DTO> user4DTOS = new ArrayList<>();
        for(User4 user4 : user4s){
            user4DTOS.add(user4.toDTO());
        }
        */

        // Stream(내부 반복자)를 이용한 Entity 리스트를 DTO 리스트로 변환
        List<User4DTO> users = user4s
                                .stream()
                                .map(entity -> entity.toDTO())
                                .collect(Collectors.toList());

        return users;
    }

    public void updateUser4(User4DTO user4DTO) {

        // Entity 존재여부 확인
        boolean result = user4Repository.existsById(user4DTO.getUid());

        if(result){
            // DTO를 Entity로 변환
            User4 entity = user4DTO.toEntity();

            // Entity수정(데이터베이스 Update)
            user4Repository.save(user4DTO.toEntity());
        }

    }

    public void deleteUser4(String uid) {
        // Entity 삭제(데이터베이스 Delete)
        user4Repository.deleteById(uid);
    }

}
