package com.smtteam.smt.dao;

import com.smtteam.smt.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReleaseDao extends JpaRepository<Release, Integer> {

    List<Release> findByProIdOrderByPosId(int proId);

    @Query("select max(r.id) from Release r ")
    Integer findMaxID();

    @Modifying
    @Query("update Release set posId=posId+1 where proId=:proId and posId>:posId")
    void updateCreatePosID(@Param("posId") int posId, @Param("proId") int proId);
}
