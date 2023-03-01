package com.bms.bms.repo;

import com.bms.bms.entity.FlatNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepo extends JpaRepository<FlatNumber,Integer> {

      public List<FlatNumber> findBymobNo(Long mobNo);
      public List<FlatNumber> findBypwd(String pwd);

}
