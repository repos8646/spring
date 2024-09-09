package com.ch07.repository;

import com.ch07.entity.Child;
import com.ch07.entity.Parent;
import com.ch07.entity.User1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class User1RepositoryTest {

    @Autowired
    private User1Repository user1Repository;

    @Test
    void findUser1ByUid(){
        User1 entity = user1Repository.findUser1ByUid("a101");

        System.out.println(entity);
    }

    @Test
    void findUser1ByName(){
        List<User1> users = user1Repository.findUser1ByName("김춘추");
        System.out.println(users);
    }

    @Test
    void findUser1ByNameNot(){
        List<User1> users = user1Repository.findUser1ByNameNot("김춘추");
        System.out.println(users);
    }

    @Test
    void findUser1ByUidAndName(){
        User1 entity = user1Repository.findUser1ByUidAndName("a101", "김춘추");

        System.out.println(entity);
    }

    @Test
    void findUser1ByUidOrName(){
        List<User1> users = user1Repository.findUser1ByUidOrName("a101", "김춘추");
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeGreaterThan(){
        List<User1> users = user1Repository.findUser1ByAgeGreaterThan(25);
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeGreaterThanEqual(){
        List<User1> users = user1Repository.findUser1ByAgeGreaterThanEqual(25);
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeLessThan(){
        List<User1> users = user1Repository.findUser1ByAgeLessThan(25);
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeLessThanEqual(){
        List<User1> users = user1Repository.findUser1ByAgeLessThanEqual(25);
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeBetween(){
        List<User1> users = user1Repository.findUser1ByAgeBetween(25, 33);
        System.out.println(users);
    }


    @Test
    void selectUser1UnderAge30() {
    }

    @Test
    void selectUser1WhereName() {
    }

    @Test
    void selectUser1WhereNameParam() {
    }

    @Test
    void selectFromParentJoinChild() {
        List<Object[]> list = user1Repository.selectFromParentJoinChild("P101");

        System.out.println(list.size());

        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));

            Parent parent = (Parent) obj[0];
            Child child = (Child) obj[1];

            System.out.println(parent);
            System.out.println(child);
        }
    }
}
