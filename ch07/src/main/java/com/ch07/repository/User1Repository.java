package com.ch07.repository;

import com.ch07.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 사용하는 엔티티 타입과 해당 엔티티의 @ID 컬럼 속성 타입 선언
@Repository
public interface User1Repository extends JpaRepository<User1, String> {

    // 사용자 정의 쿼리 메서드
    public User1 findUser1ByUid(String uid); // select * from user1 where uid=?
    public List<User1> findUser1ByName(String name); // select * from user1 where name=?
    public List<User1> findUser1ByNameNot(String name); // select * from user1 where name != ?

    public User1 findUser1ByUidAndName(String uid, String name);
    public List<User1> findUser1ByUidOrName(String uid, String name);

    public List<User1> findUser1ByAgeGreaterThan(int age);
    public List<User1> findUser1ByAgeGreaterThanEqual(int age);
    public List<User1> findUser1ByAgeLessThan(int age);
    public List<User1> findUser1ByAgeLessThanEqual(int age);
    public List<User1> findUser1ByAgeBetween(int low, int high);



    //public List<User1> findUser1ByOrderByName();
    //public List<User1> findUser1ByOrderByAgeAsc();
    //public List<User1> findUser1ByOrderByAgeDesc();
    //public List<User1> findUser1ByOrderByAgeGreaterThanOrderByAgeDesc(int age);

    //public int countUser1ByUid(String uid);
    //public int countUser1ByName(String name);

    // JPQL : JPA Native SQL
    // User1은 Entity라서 별칭을 지정해서 사용해야 함
    @Query("select u1 from User1 as u1 where u1.age < 30")
    public List<User1> selectUser1UnderAge30(String uid);

    @Query("select u1 from User1 as u1 where u1.name = ?1")
    public List<User1> selectUser1WhereName(String name);

    @Query("select u1 from User1 as u1 where u1.name = :name")
    public List<User1> selectUser1WhereNameParam(@Param("name") String name);

    @Query("select p, c from Parent as p " +
            "join Child as c on p.pid = c.parent " +
            "where p.pid = :pid")
    public List<Object[]> selectFromParentJoinChild(@Param("pid") String pid);


}
