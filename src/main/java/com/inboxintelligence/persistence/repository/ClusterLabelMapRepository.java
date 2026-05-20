package com.inboxintelligence.persistence.repository;

import com.inboxintelligence.persistence.model.entity.ClusterLabelMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClusterLabelMapRepository extends JpaRepository<ClusterLabelMap, Long> {

    Optional<ClusterLabelMap> findByClusterId(Long clusterId);

    List<ClusterLabelMap> findByLabelId(Long labelId);

    void deleteByClusterId(Long clusterId);

    void deleteByClusterIdIn(List<Long> clusterIds);
}
