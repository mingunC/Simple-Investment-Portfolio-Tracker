package com.cmgg919.tracker.repository;

import com.cmgg919.tracker.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByUserId(String userId);
}