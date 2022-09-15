package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmple;
import com.Admi.Tech.Service.ServEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContEmple {

    @Autowired
    ServEmple servEmple;
    ServEmpre servEmpre;

    @GetMapping({"/VerEmpleados"})
    public String viewEmple(Model model,@ModelAttribute("mensaje") String mensaje) {
        List<Empleado> empleList = servEmple.getAllEmpleados();
        model.addAttribute("emplelist", empleList);
        model.addAttribute("mensaje",mensaje);
        return "verEmpleados";
    }
    //agregar empresa
    @GetMapping("/AgregarEmpleado")
    public String newEmple(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado emple = new Empleado();
        model.addAttribute("emple",emple);
        model.addAttribute("mensaje",mensaje);
        //List<Empresa> listEmpre = servEmpre.getAllEmpresas();
        //model.addAttribute("emprelist", listEmpre);
        return "agregarEmpleado";
    }
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado emple, RedirectAttributes redirectAttributes){
        if(servEmple.saOrUpEmple(emple)==true){
            //mensaje de accion corresta
            redirectAttributes.addAttribute("mensaje","SAVE OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarEmleados";
    }
    @GetMapping("/EditarEmpleado/{id}")
    public String editEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado emple = servEmple.getEmpleID(id).get();
        model.addAttribute("emple",emple);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listEmpre = servEmpre.getAllEmpresas();
        model.addAttribute("emprelist", listEmpre);
        return "editarEmpleado";
    }
    @PostMapping("/ActualizarEmpleado")
    public String upEmpleado(@ModelAttribute("emple") Empleado emple, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("mensaje","UPDATE OK");
        if(servEmple.saOrUpEmple(emple)){
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarEmpleado";
    }
    @GetMapping("/EliminarEmpleado/{id}")
    public String delEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            servEmple.deleEmple(id);
        }catch (Exception e){
            redirectAttributes.addAttribute("mensaje","DELET OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addAttribute("mensaje","DELET ERROR");
        return "redirect:/VerEmpleadosb";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public  String verEmplePorEmpre(@PathVariable("id")Integer id, Model model){
        List<Empleado> listEmple = servEmple.obPorEmpre(id);
        model.addAttribute("emplelist", listEmple);
        return "verEmpleados";
    }

}
