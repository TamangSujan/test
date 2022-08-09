package com.ayata.test.controller;

import com.ayata.test.model.User;
import com.ayata.test.repo.DatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ControllerClass {
    ArrayList<User> list;

    @Autowired
    private DatabaseRepo databaseRepo;

    @RequestMapping("/")
    public ModelAndView firstPage(){
        return new ModelAndView("redirect:/login.jsp");
    }

    @PostMapping(value = "/")
    public void getLogindata(User user)
    {
        System.out.println(user.getUsername() +" "+ user.getPassword()+"");
    }

    /*
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return list;
    }

    @PostMapping("/addUser/{name}/{password}")
    public String addUser(@PathVariable("name") String username, @PathVariable String password){
        list.add(new User(username, ""+Math.random()));
        return "added";
    }

    @DeleteMapping("/deleteUser/{name}/{password}")
    public String deleteUser(@PathVariable String name, @PathVariable String password){
        list.remove(new User(name,password));
        return "removed";
    }

    @PutMapping("/updatePassword/{name}/{password}")
    public String updateUsername(@PathVariable String name, @PathVariable String password){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getUsername().equals(name)){
                list.get(i).setPassword(password);
                break;
            }
        }
        return "updated";
    }
    */

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return databaseRepo.findAll();
    }

    @PostMapping("/addUser/{name}/{password}")
    public String addUser(@PathVariable("name") String username, @PathVariable String password){
        databaseRepo.save(new User(20790423, username, password));
        return "added";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id){
        databaseRepo.deleteById(Integer.parseInt(id));
        return "removed";
    }
}