package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Entity;
import java.util.List;

@Controller
public class ContFull {
    @Autowired
   ServFactory servFactory;
//Get
    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresa(Model model){
        List<Empresa> empresaList = servFactory.getAllEmpresas();
        model.addAttribute("factorylist",empresaList);
        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String newEmpresa(Model model){
        Empresa fac = new Empresa();
        model.addAttribute("fac",fac);
        return "agregarEmpresa";
    }
    //Post
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa fac, RedirectAttributes redirectAttributes){
        if(servFactory.saOrUpFactory(fac)==true){
            return "redirect:/VerEmpresas";
        }
        return "redirect:/AgregarEmpresa";
    }


}
