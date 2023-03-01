package com.bms.bms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="FloorNumber")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floorId;

//    @OneToOne(cascade= CascadeType.ALL)
//    @JsonManagedReference
//    private FlatWithNumber flatWithNumber;

    //success
    @OneToOne(cascade= CascadeType.ALL)
    @JsonManagedReference
    private FlatWithNumber flatWithNumber;


//    @OneToOne(cascade= CascadeType.ALL)
//    @JsonManagedReference
//    private FlatWithNumber flatWithNumber;
}
