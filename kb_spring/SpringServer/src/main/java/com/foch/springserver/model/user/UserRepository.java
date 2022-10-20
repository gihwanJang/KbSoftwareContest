package com.foch.springserver.model.user;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    //유저 id을 통해 type을 수정하는 함수
    @Modifying
    @Transactional
    @Query(value = "update user set type = :type where user.id = :name", nativeQuery = true)
    void changeValue(@Param("name") String username, @Param("type") int type);

    //userId를 통해 유저가 가지고 있는 point수정
    @Modifying
    @Transactional
    @Query(value = "update user set point = :point where user.id = :id", nativeQuery = true)
    void changePoint(@Param("point") int point, @Param("id") String id);

    @Transactional
    @Query(value = "select * from user where user.id = :id", nativeQuery = true)
    User getUser(@Param("id") String id);
}
