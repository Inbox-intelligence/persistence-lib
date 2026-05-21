package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.entity.ClusterLabelMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClusterLabelMapRepository extends JpaRepository<ClusterLabelMap, Long> {

    Optional<ClusterLabelMap> findByClusterId(Long clusterId);

    List<ClusterLabelMap> findByLabelId(Long labelId);

    @Modifying
    @Query("DELETE FROM ClusterLabelMap clm WHERE clm.gmailMailboxId = :mailboxId")
    void deleteByGmailMailboxId(@Param("mailboxId") Long mailboxId);
}
