package com.inboxintelligence.persistence.service;

import com.inboxintelligence.persistence.model.LabelSource;
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
    public List<Label> findByMailboxIdAndSource(Long gmailMailboxId, LabelSource source) {
        return labelRepository.findByGmailMailboxIdAndSource(gmailMailboxId, source);
    }

    @Transactional
    public List<Label> saveAll(List<Label> labels) {
        return labelRepository.saveAll(labels);
    }
}
