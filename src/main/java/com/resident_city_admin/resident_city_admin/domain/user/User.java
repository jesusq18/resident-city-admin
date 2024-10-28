package com.resident_city_admin.resident_city_admin.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    private String city;
    @CreationTimestamp
    @Column(name = "registration_date", nullable = false, updatable = false)
    private Timestamp registrationDate;

}
