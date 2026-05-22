package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByGmailMailboxId(Long gmailMailboxId);

    Optional<Label> findByGmailMailboxIdAndFullName(Long gmailMailboxId, String fullName);

    void deleteByGmailMailboxIdAndFullNameIn(Long gmailMailboxId, List<String> fullNames);

    void deleteByGmailMailboxId(Long gmailMailboxId);
}
