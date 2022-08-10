package com.ayata.test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    @Id
    @Column(name="deptid")
    private int deptid;
    @Column(name = "role")
    private String role;
    @Column(name = "position")
    private String position;
}
