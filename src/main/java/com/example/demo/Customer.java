package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[а-яА-Яa-zA-Z\\s]+$")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp="^[а-яА-Яa-zA-Z\\s]+$")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank (message = "Address cannot be empty")
    @Column(name = "address")
    private String address;

    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$")
    @Column(name = "birth")
    private String birth;

    
    @Column (name = "total_amount")
    private Double totalAmount;



}
