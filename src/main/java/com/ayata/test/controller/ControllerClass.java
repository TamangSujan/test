package com.ayata.test.controller;

import com.ayata.test.model.Comments;
import com.ayata.test.model.Employee;
import com.ayata.test.model.Tutorial;
import com.ayata.test.model.User;
import com.ayata.test.repo.CrudRepo;
import com.ayata.test.repo.DatabaseRepo;
import com.ayata.test.repo.OneToManyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerClass {
    CrudRepo crudRepo;
    ControllerClass(){
        crudRepo = new CrudRepo() ;
    }

    @Autowired
    OneToManyRepo oneToManyRepo;

    @GetMapping("/getUser/{id}")
    public Employee getUser(@PathVariable String id){
        return crudRepo.findById(Integer.parseInt(id));
    }

    @GetMapping("/getAllUsers")
    public List<Employee> getAllUsers(){
        return crudRepo.findAll();
    }

    @PostMapping("/addUser/{id}/{name}/{deptid}")
    public void addUser(@PathVariable String id, @PathVariable String name, @PathVariable String deptid){
        crudRepo.save(new Employee(Integer.parseInt(id), name, crudRepo.getDept(Integer.parseInt(deptid))));
    }

    @GetMapping("/getAllComments")
    public List<Comments> getAllComments(){
        return oneToManyRepo.findAll();
    }
}