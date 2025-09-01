package com.practice.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByBookId(Long bookId);
    List<Inventory> findAllByRegion(String region);
    List<Inventory> findAllByBookIdAndRegion(Long bookId, String region);
    Optional<Inventory> findByBookIdAndRegionAndFormat(Long bookId, String region, String format);
}
