package com.bms.bms.controller;

import com.bms.bms.entity.FlatNumber;
import com.bms.bms.helper.Helper;
import com.bms.bms.service.FlatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/flat")
public class FlatController {

    @Autowired
    private FlatService flatService;

    //post user
   @PostMapping("/newpost")
    public ResponseEntity<?> createFlatNumber(@Valid @RequestBody FlatNumber f){

        List<FlatNumber> newMob= flatService.getDataBymobno(f.getMobNo());
        List<FlatNumber> newPwd =flatService.getDataBypwd(f.getPwd());

            if(!newMob.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Mobile Number Allready exists");
        }
            if(!newPwd.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create new password");
            }
        flatService.postFlat(f);
        return ResponseEntity.ok(Map.of("message","successful"));

    }

    //getting data by login using pwd and mobile
    @PostMapping("/login")
    public ResponseEntity<?> getDatafromUser( @RequestBody FlatNumber f){

        List<FlatNumber> newMob= flatService.getDataBymobno(f.getMobNo());
        List<FlatNumber> newpwd= flatService.getDataBypwd(f.getPwd());

        if(newMob.isEmpty() || newpwd.isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user Login!");
        }
    //    FlatNumber result = null;
        FlatNumber result = (FlatNumber) flatService.getDataBymobno(Long.valueOf(0));

        //Second verification, if any misbehave of api and returning the data not based on entered mobile number.
//        for (FlatNumber flat : newMob){
//            if(flat.getMobNo().equals(f.getMobNo())){
//                result = flat;
//                break;
//            }
//        }


        if(result == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number not found in the database !");
        }


        if(!f.getPwd().trim().isEmpty() && result.getPwd().equals(f.getPwd())){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password !");
        }

    }


    //post by excel sheet
    @PostMapping("/file")
    public ResponseEntity<?> upload(@RequestParam("file") @Valid MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.flatService.saveBulckData(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));

        } else if(file==null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload file");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Upload valid file");
        }
    }


    @GetMapping("/allFlat")
    public List<FlatNumber> getAllFlat(){
        return flatService.getAll();
    }


 //get by flat registration number
    @GetMapping("/{id}")
    public Optional<FlatNumber> getByIdFlat(@PathVariable int id){
        return flatService.getById(id);
    }

// get by mobile number
    @GetMapping("/mob/{mobNo}")
    public ResponseEntity<?> bymobNo(@PathVariable Long mobNo){
    List<FlatNumber> byId= flatService.getDataBymobno(mobNo);
    return ResponseEntity.ok().body(byId);
}


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlat(@RequestBody FlatNumber flat, @PathVariable int id){

        FlatNumber number= flatService.takeAllData();
        List<FlatNumber> newMob= flatService.getDataBymobno(flat.getMobNo());

//number!= null && flat.getRegstriationNo()== number.getRegstriationNo()+1 &&
            if ( !newMob.isEmpty()){
                return  ResponseEntity.ok().body("Mobile number already exists ................");
            }
            else if (flatService.getById(id).isEmpty()) {
                return  ResponseEntity.ok().body("Enter a valid registration number................");
            } else{
                FlatNumber flatNumber = flatService.updateById(flat, id);
                return  ResponseEntity.status(HttpStatus.OK).body("Updated Data........");

            }



 }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFlatById(@PathVariable int id){
        try{
            flatService.deletebyId(id);
      //      return ResponseEntity.status(HttpStatus.GONE).body("Deleted Id : "+id );
            return ResponseEntity.ok().body("Deleted Id : "+id );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter valid Id");
        }

    }

}
