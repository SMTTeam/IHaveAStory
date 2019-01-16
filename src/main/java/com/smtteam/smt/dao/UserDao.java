package com.smtteam.smt.dao;

import com.smtteam.smt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-28 23:24
 * 包名：com.smtteam.smt.dao
 */

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findByEmailLike(String email);

    User findByEmail(String email);

    User findByEmailAndPsw(String email , String psw);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("UPDATE User user SET user.username = :username WHERE user.email = :useremail")
    int updateUserInfoByEmail(@Param("username") String username, @Param("useremail") String useremail);
}
