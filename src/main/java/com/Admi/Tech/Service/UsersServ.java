package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.UsersModel;
import com.Admi.Tech.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServ {
    @Autowired
    private final UsersRepo usersRepo;

    public UsersServ(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public UsersModel registerUser( String login, String password, String email){
        if(login != null && password != null) {
            return null;
        }else {
            if(usersRepo.findFirstByLogin(login).isPresent()){
                System.out.println("duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepo.save(usersModel);
        }
    }
    public UsersModel authenticate(String login, String password){
        return  usersRepo.findByLoginAndPassword(login, password).orElse(null);
    }
}