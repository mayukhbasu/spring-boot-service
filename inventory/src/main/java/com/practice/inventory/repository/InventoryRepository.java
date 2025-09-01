package com.practice.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByBookId(String bookId);
    List<Inventory> findAllByRegion(String region);
    List<Inventory> findAllByBookIdAndRegion(String bookId, String region);
    Optional<Inventory> findByBookIdAndRegionAndFormat(String bookId, String region, String format);
}
