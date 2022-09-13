package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContEmpre {
    @Autowired
    ServEmpre servEmpre;
    //Get ver empresas
    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresa(Model model){
        List<Empresa> empresaList = servEmpre.getAllEmpresas();
        model.addAttribute("emprelist",empresaList);
        return "verEmpresas";
    }
    //agregar empresa
    @GetMapping("/AgregarEmpresa")
    public String newEmpresa(Model model){
        Empresa empre = new Empresa();
        model.addAttribute("empre",empre);
        return "agregarEmpresa";
    }
    //Post guardar empresa
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa empre, RedirectAttributes redirectAttributes){
        if(servEmpre.saOrUpEmpre(empre)==true){
            //mensaje de accion corresta
            redirectAttributes.addAttribute("mensaje","SAVE OK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addAttribute("mensaje","SAVE ERRORr");
        return "redirect:/AgregarEmpresa";
    }
    //editar empresas
    @GetMapping("/EditarEmpresa/{id}")
    public String editEmpresa(Model model, @PathVariable Integer id){
        Empresa empre = servEmpre.getEmpreyID(id);
        model.addAttribute("empre",empre);
        return "editarEmpresa";
    }
    //actualizar empresa
    @PostMapping("/ActualizarEmpresa")
    public String upEmpresa(@ModelAttribute("empre") Empresa empre, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("mensaje","UPDATE OK");
        if(servEmpre.saOrUpEmpre(empre)){
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarEmpresa";
    }
    //eliminar
    @GetMapping("/EliminarEmpresa/{id}")
    public String delEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            servEmpre.deletEmpre(id);
        }catch (Exception e){
            redirectAttributes.addAttribute("mensaje","DELET ERROR");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addAttribute("mensaje","DELET OK");
        return "redirect:/VerEmpresas";
    }


}