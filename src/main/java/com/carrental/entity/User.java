
package com.carrental.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String licenseNumber;
    private String contact;
    private String driversLicense;
    private String username;


    @Enumerated(EnumType.STRING)
    private Role role;
}
