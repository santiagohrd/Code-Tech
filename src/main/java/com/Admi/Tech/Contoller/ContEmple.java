package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmple;
import com.Admi.Tech.Service.ServEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
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
        List<Empresa> listEmpre = servEmpre.getAllEmpresas();
        model.addAttribute("emprelist", listEmpre);
        return "agregarEmpleado";
    }
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado emple, RedirectAttributes redirectAttributes){
        String passEncrip = passwordEncoder().encode(emple.getPassword());
        emple.setPassword(passEncrip);
        if(servEmple.saOrUpEmple(emple)==true){
            //mensaje de accion corresta
            redirectAttributes.addAttribute("mensaje","SAVE OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarEmpleado";
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
        Integer id=emple.getId();
        String contAnt=servEmple.getEmpleID(id).get().getPassword();
        if(!emple.getPassword().equals(contAnt)){
            String passEncrip = passwordEncoder().encode(emple.getPassword());
            emple.setPassword(passEncrip);
        }
        if(servEmple.saOrUpEmple(emple)){
        redirectAttributes.addFlashAttribute("mensaje","UPDATE OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarEmpleado/" + emple.getId();
    }
    @GetMapping("/EliminarEmpleado/{id}")
    public String delEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(servEmple.deleEmple(id)){
            redirectAttributes.addFlashAttribute("mensaje","DELET OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","DELET ERROR");
        return "redirect:/VerEmpleados";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public  String verEmplePorEmpre(@PathVariable("id")Integer id, Model model){
        List<Empleado> listEmple = servEmple.obPorEmpre(id);
        model.addAttribute("emplelist", listEmple);
        return "verEmpleados";
    }
@RequestMapping(value = "/Error")
public String error(){
        return "AccDene";
}

    //Encriptar contraseña
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

}
