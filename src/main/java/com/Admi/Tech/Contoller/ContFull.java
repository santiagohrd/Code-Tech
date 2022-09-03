package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import java.util.List;

@Controller
public class ContFull {
    @Autowired
   ServFactory servFactory;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresa(Model model){
        List<Empresa> empresaList = servFactory.getAllEmpresas();
        model.addAttribute("factorylist",empresaList);
        return "verEmpresas";
    }

}
