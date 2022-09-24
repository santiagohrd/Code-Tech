package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.UsersModel;
import com.Admi.Tech.Service.UsersServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersCont {


    private final UsersServ usersServ;

    public UsersCont(UsersServ usersServ) {
        this.usersServ = usersServ;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "/register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "/login_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersServ.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";

    }
    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticate = usersServ.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if(authenticate != null){
            model.addAttribute("userLogin", authenticate.getLogin());
            return "personal_page";
        }else {
            return "error_page";
        }
    }

}

