package com.vivek.commerce.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_address")
public class Address  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CommerceUser user;
}
