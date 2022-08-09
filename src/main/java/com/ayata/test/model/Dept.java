package com.ayata.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    @Id
    @Column(name="deptid")
    private int deptid;
    @Column(name = "role")
    private String role;
    @Column(name = "position")
    private String position;

    @OneToOne(mappedBy = "dept")
    private Employee employee;
}
