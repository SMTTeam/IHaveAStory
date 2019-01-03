package com.smtteam.smt.dao;

import com.smtteam.smt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
