package com.smtteam.smt.dao;

import com.smtteam.smt.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-28 23:24
 * 包名：com.smtteam.smt.dao
 */

@Repository
public interface ActivityDao extends JpaRepository<Activity, Integer> {
    @Modifying
    @Query("update Activity set posId=posId+1 where proId=:proId and posId>:posId")
    void updateCreatePosID(@Param("proId") int proId, @Param("posId") int posId);

    List<Activity> findByProIdOrderByPosId(int proId);

    @Query("select max(a.id) from Activity a ")
    Integer findMaxID();
}
