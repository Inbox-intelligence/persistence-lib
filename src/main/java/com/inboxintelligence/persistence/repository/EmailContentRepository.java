package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.ProcessedStatus;
import com.inboxintelligence.persistence.model.entity.EmailContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailContentRepository extends JpaRepository<EmailContent, Long> {

    boolean existsByGmailMailboxIdAndMessageId(Long gmailMailboxId, String messageId);

    @Query("SELECT ec.messageId FROM EmailContent ec WHERE ec.gmailMailboxId = :mailboxId")
    List<String> findMessageIdsByGmailMailboxId(@Param("mailboxId") Long mailboxId);

    @Modifying
    @Query("""
            UPDATE EmailContent ec
            SET ec.processedStatus = :status,
                ec.updatedAt       = CURRENT_TIMESTAMP
            WHERE ec.id IN :ids
            """)
    void bulkUpdateStatus(@Param("ids") List<Long> ids, @Param("status") ProcessedStatus status);
}
