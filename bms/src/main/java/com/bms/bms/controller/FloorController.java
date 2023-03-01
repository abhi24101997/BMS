package com.bms.bms.controller;

import com.bms.bms.entity.FlatNumber;
import com.bms.bms.entity.Floor;

import com.bms.bms.service.FlatService;
import com.bms.bms.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Component
@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @Autowired
    private FlatService flatService;

    @Autowired
    private FlatNumber flatNumber;




//    @PostMapping
//    public Floor newDAta(@RequestBody Floor floor){
//       return floorService.createNewData(floor);
//    }

    @PostMapping
    public ResponseEntity<?> newDAta(@RequestBody Floor floor){

        List<FlatNumber> filteredMobileNo =floorService.getMobileNo(floor.getFlatWithNumber().getFlatNumber().getMobNo());
         if (!filteredMobileNo.isEmpty()){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile Number");
         }

         floorService.createNewData(floor);
        return ResponseEntity.status(HttpStatus.OK).body(floor);
    }

    @GetMapping("/all")
    public List<Floor> allData(){
        List<Floor> data = floorService.getAllData();
        return data;
    }

    @GetMapping("/{id}")
    public Optional<Floor> getDataById(@PathVariable int id){
       return floorService.dataById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> dataUpdateBYId(@RequestBody Floor floor , @PathVariable int id){
        Optional<Floor> validData= (Optional<Floor>)floorService.dataById(id);
        if (!validData.isEmpty()){
             floorService.updatById(floor,id);
            return ResponseEntity.status(HttpStatus.OK).body("Data Successfull");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Id does not exists");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDataById(@PathVariable int id){
        Optional<Floor> validData= (Optional<Floor>)floorService.dataById(id);
        if (validData.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter a valid Id");
        }
        floorService.deleteBYNo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data deleted");
    }
}
