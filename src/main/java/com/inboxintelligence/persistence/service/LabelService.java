package com.inboxintelligence.persistence.service;

import com.inboxintelligence.persistence.model.entity.Label;
import com.inboxintelligence.persistence.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    @Transactional(readOnly = true)
    public List<Label> findByMailboxId(Long gmailMailboxId) {
        return labelRepository.findByGmailMailboxId(gmailMailboxId);
    }

    @Transactional(readOnly = true)
    public Optional<Label> findByMailboxIdAndFullName(Long gmailMailboxId, String fullName) {
        return labelRepository.findByGmailMailboxIdAndFullName(gmailMailboxId, fullName);
    }

    @Transactional
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Transactional
    public List<Label> saveAll(List<Label> labels) {
        return labelRepository.saveAll(labels);
    }

    @Transactional
    public void deleteByMailboxIdAndFullNames(Long gmailMailboxId, List<String> fullNames) {
        labelRepository.deleteByGmailMailboxIdAndFullNameIn(gmailMailboxId, fullNames);
    }

    @Transactional
    public void flushAndFillLabels(Long gmailMailboxId, List<Label> labels) {
        labelRepository.deleteByGmailMailboxId(gmailMailboxId);
        labelRepository.flush();
        labelRepository.saveAll(labels);
    }
}
