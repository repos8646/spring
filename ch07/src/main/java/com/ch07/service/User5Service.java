package com.ch07.service;

import com.ch07.dto.User5DTO;
import com.ch07.entity.User5;
import com.ch07.repository.User5Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class User5Service {

    // 생성자 주입
    private final User5Repository user5Repository;

    public void insertUser5(User5DTO user5DTO) {

        // DTO를 Entity로 변환
        User5 entity = user5DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        user5Repository.save(entity);
    }

    public User5DTO selectUser5(int seq){

        /*
            Optional
             - null 체크 검정을 손쉽고 안전하게 처리하기 위한 클래스
             - Spring JPA find~ 메서드의 결과 타입
        */
        Optional<User5> opt = user5Repository.findById(seq);

        // Entity 존재 확인 후 Optional 타입으로 정의된 Entity 가져오기
        if(opt.isPresent()){
            User5 user5 = opt.get();

            // Entity를 DTO로 변환해서 반환
            return user5.toDTO();
        }
        return null;
    }

    public List<User5DTO> selectUser5s(){

        // Entity 전체 조회
        List<User5> user5s = user5Repository.findAll();

        // for문(외부 반복자)으로 Entity 리스트를 DTO 리스트로 변환
        /*List<User5DTO> user5DTOS = new ArrayList<>();
        for(User5 user5 : user5s){
            user5DTOS.add(user5.toDTO());
        }
        */

        // Stream(내부 반복자)를 이용한 Entity 리스트를 DTO 리스트로 변환
        List<User5DTO> users = user5s
                                .stream()
                                .map(entity -> entity.toDTO())
                                .collect(Collectors.toList());

        return users;
    }

    public void updateUser5(User5DTO user5DTO) {

        // Entity 존재여부 확인
        boolean result = user5Repository.existsById(user5DTO.getSeq());

        if(result){
            // DTO를 Entity로 변환
            User5 entity = user5DTO.toEntity();

            // Entity수정(데이터베이스 Update)
            user5Repository.save(user5DTO.toEntity());
        }

    }

    public void deleteUser5(int seq) {
        // Entity 삭제(데이터베이스 Delete)
        user5Repository.deleteById(seq);
    }

}
