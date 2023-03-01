package com.bms.bms.service;

import com.bms.bms.entity.FlatNumber;
import com.bms.bms.entity.Floor;
import com.bms.bms.repo.FlatRepo;
import com.bms.bms.repo.FloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    @Autowired
    private FloorRepo floorRepo;
    @Autowired
    private FlatRepo flatRepo;


//    @Autowired
//    private FlatNumber flatNumber;

    //create new data by post
    public Floor createNewData(Floor floor){
        return floorRepo.save(floor);
    }

    //get all data
    public List<Floor> getAllData(){
        return floorRepo.findAll();
    }

    //get by floor number
    public Optional<Floor> dataById(int id){
        return floorRepo.findById(id);
    }

    //update data by floor number
    public Floor updatById(Floor f, int id){
        f.setFloorId(id);
        return floorRepo.save(f);

    }

    //delete data by floor number
    public void deleteBYNo(int id){
        floorRepo.deleteById(id);
    }


    //get mobile number
    public List<FlatNumber> getMobileNo(Long id){
        List<FlatNumber> getMobNo = flatRepo.findBymobNo(id);
        return getMobNo;
    }
}
