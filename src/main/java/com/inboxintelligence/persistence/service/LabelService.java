package com.inboxintelligence.persistence.service;

import com.inboxintelligence.persistence.model.entity.Label;
import com.inboxintelligence.persistence.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    @Transactional(readOnly = true)
    public List<Label> findByMailboxId(Long gmailMailboxId) {
        return labelRepository.findByGmailMailboxId(gmailMailboxId);
    }

    @Transactional
    public List<Label> saveAll(List<Label> labels) {
        return labelRepository.saveAll(labels);
    }

    @Transactional
    public void deleteByMailboxIdAndFullNames(Long gmailMailboxId, List<String> fullNames) {
        labelRepository.deleteByGmailMailboxIdAndFullNameIn(gmailMailboxId, fullNames);
    }
}
