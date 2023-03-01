package com.bms.bms.repo;

import com.bms.bms.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepo extends JpaRepository<Floor,Integer> {
}
