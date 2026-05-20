package com.inboxintelligence.persistence.service;

import com.inboxintelligence.persistence.model.entity.ClusterLabelMap;
import com.inboxintelligence.persistence.repository.ClusterLabelMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClusterLabelMapService {

    private final ClusterLabelMapRepository clusterLabelMapRepository;

    @Transactional(readOnly = true)
    public Optional<ClusterLabelMap> findByClusterId(Long clusterId) {
        return clusterLabelMapRepository.findByClusterId(clusterId);
    }

    @Transactional(readOnly = true)
    public List<ClusterLabelMap> findByLabelId(Long labelId) {
        return clusterLabelMapRepository.findByLabelId(labelId);
    }

    @Transactional
    public ClusterLabelMap save(ClusterLabelMap clusterLabelMap) {
        return clusterLabelMapRepository.save(clusterLabelMap);
    }

    @Transactional
    public List<ClusterLabelMap> saveAll(List<ClusterLabelMap> clusterLabelMaps) {
        return clusterLabelMapRepository.saveAll(clusterLabelMaps);
    }

    @Transactional
    public void deleteByClusterId(Long clusterId) {
        clusterLabelMapRepository.deleteByClusterId(clusterId);
    }

    @Transactional
    public void deleteByClusterIds(List<Long> clusterIds) {
        clusterLabelMapRepository.deleteByClusterIdIn(clusterIds);
    }
}
