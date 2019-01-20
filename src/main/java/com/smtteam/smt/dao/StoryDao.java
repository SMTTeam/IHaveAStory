package com.smtteam.smt.dao;

import com.smtteam.smt.model.Story;
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
public interface StoryDao extends JpaRepository<Story, Integer> {
    @Modifying
    @Query("update Story set posId=posId+1 where posId>:posId")
    void updateCreatePosID(@Param("posId") int posId);

    List<Story> findByTaskIdOrderByPosIdDesc(int taskId);

    List<Story> findByTaskId(int taskId);

    @Query("select max(s.posId) from Story s ")
    Integer findMaxPosID();

//    @Query("SELECT new map(s.iteration,s.groupName) FROM Story s WHERE task_id IN (\n" +
//            "  SELECT id FROM Task WHERE activity_id IN (\n" +
//            "    SELECT id FROM Activity WHERE pro_id=:proId\n" +
//            "  )\n" +
//            ")ORDER BY iteration")
//    List<Map<String,IterationVO>> findIterByProId(@Param("proId")int proId);
}
