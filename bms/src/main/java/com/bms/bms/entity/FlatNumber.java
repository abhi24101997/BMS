package com.bms.bms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
@Table( name="User" )

public class FlatNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="regstriationNo")
    private int regstriationNo;

    @NotBlank(message = "Should not be blank")
    private String firstname;

    @NotBlank(message = "Should not be blank")
    private String lastName;

    @Email(message = "enter the valid email")
    private String email;

 //   @NotBlank
 //   @Pattern(regexp = "^\\d{10}$")
     @Max(9999999999l)
     @Min(1000000000)
 //    @NotBlank
     private Long mobNo;


    @NotBlank
    private String pwd;

    //success
    @OneToOne(mappedBy="flatNumber")
    @JsonBackReference
    private FlatWithNumber flatWithNumber;

//    @OneToMany(mappedBy="flatNumber")
//    @JsonBackReference
//    private FlatWithNumber flatWithNumber;


//    @OneToOne()
//    @JsonBackReference
//    private FlatWithNumber flatWithNumber;



}
