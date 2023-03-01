package com.bms.bms.repo;

import com.bms.bms.entity.FlatWithNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlatWithNumberRepo extends JpaRepository<FlatWithNumber,Integer> {



}
