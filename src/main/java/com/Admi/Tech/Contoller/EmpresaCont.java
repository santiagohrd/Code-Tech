package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.EmpleadoServ;
import com.Admi.Tech.Service.EmpresaServ;
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
public class EmpresaCont {

    @Autowired
    EmpresaServ empresaServ;

    @Autowired
    EmpleadoServ empleadoServ;


    @GetMapping({"/VerEmpresas"})
    public String viewEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> empreList = empresaServ.getAllEmpresas();
        model.addAttribute("emprelist",empreList);
        model.addAttribute("mensaje",mensaje);
        return "ver_Empresas";
    }

    //agregar empresa
    @GetMapping("/AgregarEmpresa")
    public String newEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa empre = new Empresa();
        model.addAttribute("empre",empre);
        model.addAttribute("mensaje",mensaje);
        return "agregar_Empresa";
    }
    //Post guardar empresa
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa empre, RedirectAttributes redirectAttributes){
        if(empresaServ.savOrUpEmpresa(empre)==true){
            redirectAttributes.addFlashAttribute("mensaje","SAVE OK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarEmpresa";
    }
    //editar empresas
    @GetMapping("/EditarEmpresa/{id}")
    public String editEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa empre = empresaServ.getEmpresaById(id);
        model.addAttribute("empre",empre);
        model.addAttribute("mensaje", mensaje);
        return "editar_Empresa";
    }
    //actualizar empresa
    @PostMapping("/ActualizarEmpresa")
    public String upEmpresa(@ModelAttribute("empre") Empresa empre, RedirectAttributes redirectAttributes){
        if(empresaServ.savOrUpEmpresa(empre)){
            redirectAttributes.addFlashAttribute("mensaje","UPDATE OK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarEmpresa/" + empre.getId();
    }
    //eliminar
    @GetMapping("/EliminarEmpresa/{id}")
    public String delEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(empresaServ.delEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","DELET OK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","DELET ERROR");
        return "redirect:/VerEmpresas";
    }

}