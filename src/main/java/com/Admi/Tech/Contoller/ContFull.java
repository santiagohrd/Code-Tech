package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Repository.RepoFactory;
import com.Admi.Tech.Service.ServFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Entity;
import java.util.List;

@Controller
public class ContFull {
    @Autowired
   ServFactory servFactory;
//Get ver empresas
    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresa(Model model){
        List<Empresa> empresaList = servFactory.getAllEmpresas();
        model.addAttribute("factorylist",empresaList);
        return "verEmpresas";
    }
//agregar empresa
    @GetMapping("/AgregarEmpresa")
    public String newEmpresa(Model model){
        Empresa fac = new Empresa();
        model.addAttribute("fac",fac);
        return "agregarEmpresa";
    }
    //Post guardar empresa
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa fac, RedirectAttributes redirectAttributes){
        if(servFactory.saOrUpFactory(fac)==true){
            redirectAttributes.addAttribute("mensaje","Guardo correctamente");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addAttribute("mensaje","Hubo un error");
        return "redirect:/AgregarEmpresa";
    }
//editar empresas
    @GetMapping("/EditarEmpresa/{id}")
    public String editEmpresa(Model model, @PathVariable Integer id){
        Empresa fac = servFactory.getFactoryID(id);
        model.addAttribute("fac",fac);
        return "editarEmpresa";
    }
//actualizar empresa
@PostMapping("/ActualizarEmpresa")
public String upEmpresa(@ModelAttribute("fac") Empresa fac, RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("mensaje","Actualizo correctamente");
    if(servFactory.saOrUpFactory(fac)){
        return "redirect:/VerEmpresas";
    }
    redirectAttributes.addAttribute("mensaje","Hubo un error en la actualizacion");
    return "redirect:/EditarEmpresa";
    }
    //eliminar
    @GetMapping("/EliminarEmpresa/{id}")
    public String delEmpresa(@PathVariable Integer id){
        try {
            servFactory.deletFactory(id);
        }catch (Exception e){
            return "redirect:/VerEmpresa";
        }
        return "redirect:/VerEmpresa";
    }


}
