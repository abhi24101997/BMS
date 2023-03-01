package com.bms.bms.service;

import com.bms.bms.entity.FlatNumber;
import com.bms.bms.entity.Floor;
import com.bms.bms.helper.Helper;
import com.bms.bms.repo.FlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@EnableAutoConfiguration
public class FlatService {

    @Autowired
    private FlatRepo flatRepo;

    //post
    public FlatNumber postFlat(FlatNumber flat){

        return flatRepo.save(flat);
    }

//    public FlatNumber saveUser(FlatNumber flatNumber) {
//        FlatNumber user = FlatNumber.builder().build(( flatNumber.getFirstname(),
//                flatNumber.getLastName(), flatNumber.getMobNo(), flatNumber.getEmail(), flatNumber.getPwd()));
//        return flatRepo.save(user);
//    }

//    public User saveUser(UserRequest userRequest) {
//        User user = User.
//                build(0, userRequest.getName(), userRequest.getEmail(),
//                        userRequest.getMobile(), userRequest.getGender(), userRequest.getAge(),
//                        userRequest.getNationality());
//        return repository.save(user);
//    }



    //post by excel sheet
    public Object saveBulckData(MultipartFile file){
    try{

        List<FlatNumber> flats = Helper.convertExcelToListOfProduct(file.getInputStream());

            this.flatRepo.saveAll(flats);

    }catch(IOException e){
          e.printStackTrace();
    }

    catch (Exception duplicate){
        if(duplicate.toString().contains("Duplicate")){
            System.out.println(duplicate);
        }else{
            System.out.println("Something went wrong !\n"+duplicate);
        }
    }
        return null;
    }


    //get all
    public List<FlatNumber> getAll(){
        return flatRepo.findAll();
    }

    //get by mobile number
    public List<FlatNumber> getDataBymobno(Long id){
        List<FlatNumber> newdata=  flatRepo.findBymobNo(id);

        return newdata;
    }

    //get by pwd number
    public List<FlatNumber> getDataBypwd(String pswd){
        List<FlatNumber> newpwd=  flatRepo.findBypwd(pswd);

        return newpwd;
    }

    //get by flat number
    public Optional<FlatNumber> getById(int id){
        return flatRepo.findById(id);
    }

    //update flat number
    public FlatNumber updateById(FlatNumber flat,int id){
        flat.setRegstriationNo(id);
         return flatRepo.save(flat);
    }

    //delete by id
    public void deletebyId(int id){
        flatRepo.deleteById(id);
    }

    public FlatNumber takeAllData(){
        List<FlatNumber> all = getAll();
        if(all.size()>0){
            all.get(all.size()-1);
        }
        return null;
    }
}
