package com.java.login.controller;

import com.java.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAuthenticated(@RequestParam("user") String user, @RequestParam("password") String password) {
        if(loginRepository.existUser(user,password)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("save")
    public ResponseEntity registeruser(@RequestParam("user") String user, @RequestParam("password") String password) {
        if(loginRepository.existUser(user,password)){
            return ResponseEntity.badRequest().build();
        }
        loginRepository.saveUser(user,password);
        return ResponseEntity.accepted().build();
    }

}
