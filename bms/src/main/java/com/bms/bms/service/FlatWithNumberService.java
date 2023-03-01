package com.bms.bms.service;

import com.bms.bms.entity.FlatWithNumber;
import com.bms.bms.repo.FlatWithNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlatWithNumberService {

    @Autowired
   private FlatWithNumberRepo flatWithNumberRepo;

    //post
    public FlatWithNumber create(FlatWithNumber flat){
        return flatWithNumberRepo.save(flat);
    }

    //get all
    public List<FlatWithNumber> getAll(){
        return flatWithNumberRepo.findAll();
    }

    //get by id
    public Optional<FlatWithNumber> getFlatById(int id){

        return flatWithNumberRepo.findById(id);
    }

    //update
    public void uodateFlatById(int id,FlatWithNumber flat){
        flat.setAllFlatNumber(id);
        flatWithNumberRepo.save(flat);
    }

    //delete
    public void deleteFlatNo(int id){
        flatWithNumberRepo.deleteById(id);
    }

    //give error while updating
//    public void returnById(){
//        List<FlatWithNumber> newData= getFlatById(id)
//    }
}
