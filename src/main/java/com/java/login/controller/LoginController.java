package com.java.login.controller;

import com.java.login.repository.LoginRepository;
import com.java.login.service.EncrypterDecrypterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("login")
public class LoginController {

    static final Log LOG = LogFactory.getLog(LoginController.class);

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    EncrypterDecrypterService encrypterDecrypterService;

    @PostMapping
    public ResponseEntity getAuthenticated(@RequestParam("user") String user, @RequestParam("password") String password) {
        if(loginRepository.existUser(user,password)){
            return ResponseEntity.ok(String.format("%s found", user));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("save")
    public ResponseEntity registeruser(@RequestParam("user") String user, @RequestParam("password") String password) {
        if(loginRepository.existUser(user,password)){
            return ResponseEntity.badRequest().build();
        }
        LOG.info("Saving User details");
        loginRepository.saveUser(user, encrypterDecrypterService.encryptString(password));
        return ResponseEntity.accepted().build();
    }

}
