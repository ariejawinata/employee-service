package com.ariginting.employeeservice.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "nik", length = 25)
    private String nik;

    @Column(name = "email", length = 25)
    private String email;

}
