package com.java.login.repository;

import com.java.login.entities.User;
import com.java.login.service.EncrypterDecrypterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LoginRepository {
    @Autowired
    EntityManager entityManager;

    @Autowired
    EncrypterDecrypterService encrypterDecrypterService;

    public boolean existUser(String userName,String password){

        List<?> user = entityManager.createQuery("select account from User account where userName = :name and password = :password")
                .setParameter("name",userName)
                .setParameter("password", encrypterDecrypterService.encryptString(password))
                .getResultList();

        return user != null && !user.isEmpty();
    }

    public void saveUser(String userName,String password){
        User user = new User(userName,password);
        entityManager.merge(user);
    }


}
