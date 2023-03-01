package com.bms.bms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flatNumber")
public class FlatWithNumber {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int allFlatNumber;


////success
    @OneToOne(cascade= CascadeType.ALL)
    @JsonManagedReference
    private FlatNumber flatNumber;
    @OneToOne(mappedBy = "flatWithNumber")//cascade= CascadeType.ALL
    @JsonBackReference
    private Floor floor;


//    @OneToMany(cascade= CascadeType.ALL)
//    @JsonManagedReference
//    private FlatNumber flatNumber;
//    @OneToOne(mappedBy = "flatWithNumber")//cascade= CascadeType.ALL
//    @JsonBackReference
//    private Floor floor;


//    @OneToMany(targetEntity = FlatNumber.class,cascade= CascadeType.ALL)
//    @JoinColumn(name = "regstriationNo_fk",referencedColumnName = "allFlatNumber")
//    private List<FlatNumber> flatNumber;
}
