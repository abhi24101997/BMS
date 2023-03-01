package com.bms.bms.controller;

import com.bms.bms.entity.FlatNumber;
import com.bms.bms.entity.FlatWithNumber;
import com.bms.bms.repo.FlatRepo;
import com.bms.bms.service.FlatService;
import com.bms.bms.service.FlatWithNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/newflatNo")
@Component
public class FlatWithNumberController {

    @Autowired
   private FlatWithNumberService flatWithNumberService;

    @Autowired
    private FlatService userservice;

//    @PostMapping
//    public FlatWithNumber newFlatNo(@RequestBody FlatWithNumber flat){
//        return flatWithNumberService.create(flat);
//    }


    @PostMapping
    public ResponseEntity<?> newFlatNo(@RequestBody FlatWithNumber flat){
       List<FlatNumber> filteredMobileNo = userservice.getDataBymobno(flat.getFlatNumber().getMobNo());
       if (!filteredMobileNo.isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Allready Exists");
       }
        flatWithNumberService.create(flat);
       return ResponseEntity.status(HttpStatus.OK).body("Data Saved" +flat);
    }


    @GetMapping("/allFlatsNo")
    public List<FlatWithNumber> allFlatNo(){
        return flatWithNumberService.getAll();
    }

    @GetMapping("{id}")
    public Optional<FlatWithNumber> flatById(@PathVariable int id){
        return flatWithNumberService.getFlatById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> flatNoUpdate(@PathVariable int id,@RequestBody FlatWithNumber flat){
        Optional<FlatWithNumber> updateById=flatWithNumberService.getFlatById(id);
        if (!updateById.isEmpty()){
            flatWithNumberService.uodateFlatById(id, flat);
        return ResponseEntity.status(HttpStatus.OK).body(flat);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Enter a valid Id");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlatNo(@PathVariable int id) {

        Optional<FlatWithNumber> deleteId = flatWithNumberService.getFlatById(id);
        if (deleteId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id doesnot Exists : " + id);
        }
        flatWithNumberService.deleteFlatNo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted :" + id);
    }
}
