package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByGmailMailboxId(Long gmailMailboxId);

    void deleteByGmailMailboxIdAndFullNameIn(Long gmailMailboxId, List<String> fullNames);
}
