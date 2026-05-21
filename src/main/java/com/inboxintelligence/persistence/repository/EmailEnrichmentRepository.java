package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.EmailEmbeddingProjection;
import com.inboxintelligence.persistence.model.entity.EmailEnrichment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmailEnrichmentRepository extends JpaRepository<EmailEnrichment, Long> {

    Optional<EmailEnrichment> findByEmailContentId(Long emailContentId);

    List<EmailEnrichment> findByClusterId(Long clusterId);

    @Query("""
            SELECT new com.inboxintelligence.persistence.model.EmailEmbeddingProjection(ee.id, ee.embedding)
            FROM EmailEnrichment ee
            JOIN EmailContent ec ON ee.emailContentId = ec.id
            WHERE ec.gmailMailboxId = :mailboxId
              AND ee.embedding IS NOT NULL
            """)
    List<EmailEmbeddingProjection> findIdAndEmbeddingByGmailMailboxId(@Param("mailboxId") Long mailboxId);

    @Modifying
    @Query("""
            UPDATE EmailEnrichment ee
            SET ee.clusterId             = :clusterId,
                ee.clusterAssignmentType = com.inboxintelligence.persistence.model.ClusterAssignmentType.BATCH,
                ee.clusterProbability    = NULL,
                ee.updatedAt             = CURRENT_TIMESTAMP
            WHERE ee.id IN :ids
            """)
    void bulkAssignCluster(@Param("ids") List<Long> ids, @Param("clusterId") Long clusterId);

    @Modifying
    @Query("""
            UPDATE EmailEnrichment ee
            SET ee.clusterId             = NULL,
                ee.clusterAssignmentType = com.inboxintelligence.persistence.model.ClusterAssignmentType.BATCH,
                ee.clusterProbability    = NULL,
                ee.updatedAt             = CURRENT_TIMESTAMP
            WHERE ee.id IN :ids
            """)
    void bulkUnassignCluster(@Param("ids") List<Long> ids);
}
