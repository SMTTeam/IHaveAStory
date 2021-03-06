package com.smtteam.smt.dao;

import com.smtteam.smt.model.Task;
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
public interface TaskDao extends JpaRepository<Task, Integer> {
    @Modifying
    @Query("update Task set posId=posId+1 where activityId=:activityId and posId>:posId")
    void updateCreatePosID(@Param("activityId") int activityId, @Param("posId") int posId);

    List<Task> findByActivityIdOrderByPosId(int activityId);

    @Query("select max(t.id) from Task t ")
    Integer findMaxID();

}
