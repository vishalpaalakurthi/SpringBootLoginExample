package com.java.login.controller;

import com.java.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginRepository loginRepository;

    @PostMapping
    public String getAuthenticated(@RequestParam("user") String user, @RequestParam("password") String password){
        if(loginRepository.existUser(user,password)){
            return "Valid";
        }
        return "Invalid";
    }

    @PostMapping("save")
    public String registeruser(@RequestParam("user") String user, @RequestParam("password") String password){
        if(loginRepository.existUser(user,password)){
            return "Already Existing user";
        }
        loginRepository.saveUser(user,password);
        return "saved";
    }

}
