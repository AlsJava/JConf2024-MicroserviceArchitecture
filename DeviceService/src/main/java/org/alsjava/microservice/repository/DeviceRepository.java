package org.alsjava.microservice.repository;

import org.alsjava.microservice.domain.DeviceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {

    Optional<DeviceEntity> findByUserIdAndId(UUID userId, UUID id);

    List<DeviceEntity> findAllByUserId(UUID userId, Pageable pageable);

    int countAllByUserId(UUID userId);
}
