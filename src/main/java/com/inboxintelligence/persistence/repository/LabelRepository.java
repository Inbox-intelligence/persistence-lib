package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.LabelSource;
import com.inboxintelligence.persistence.model.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByGmailMailboxIdAndSource(Long gmailMailboxId, LabelSource source);
}
