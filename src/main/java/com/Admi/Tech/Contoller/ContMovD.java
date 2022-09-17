package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovDin;
import com.Admi.Tech.Service.ServEmple;
import com.Admi.Tech.Service.ServEmpre;
import com.Admi.Tech.Service.ServMovD;
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
public class ContMovD {

    @Autowired
    ServMovD servMovD;
    @Autowired
    ServEmple servEmple;
    @Autowired
    ServEmpre servEmpre;

    @GetMapping({"/VerMovimientos"})
    public String viewMov (Model model, @ModelAttribute("mensaje") String mensaje) {
        List<MovDin> movDList = servMovD.getAllMov();
        model.addAttribute("movDlist", movDList);
        model.addAttribute("mensaje",mensaje);
        return "verMovimientos";
    }

    @GetMapping("/AgregarMovimiento")
    public String newMov (Model model, @ModelAttribute("mensaje") String mensaje){
        MovDin MovD = new MovDin();
        model.addAttribute("MovD",MovD);
        model.addAttribute("mensaje",mensaje);
        List<Empleado> listEmple = servEmple.getAllEmpleados();
        model.addAttribute("emplelist", listEmple);
        return "agregarMovimiento";
    }
    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento (MovDin movD, RedirectAttributes redirectAttributes){
        if(servMovD.saOrUpMov(movD)){
            redirectAttributes.addAttribute("mensaje","SAVE OK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarMovimiento";
    }
    @GetMapping("/EditarMovimiento/{id}")
    public String editMovimiento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovDin mov = servMovD.getMovID(id);
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listEmple = servEmple.getAllEmpleados();
        model.addAttribute("emplelist", listEmple);
        return "editarMovimiento";
    }
    @PostMapping("/ActualizarMovimiento")
    public String upMovimiento(@ModelAttribute("movD") MovDin movD, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("mensaje","UPDATE OK");
        if(servMovD.saOrUpMov(movD)){
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarMovimiento/" + movD.getId();
    }
    @GetMapping("/EliminarMovimiento/{id}")
    public String delEmMovi(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            servMovD.deleMov(id);
        }catch (Exception e){
            redirectAttributes.addAttribute("mensaje","DELET OK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addAttribute("mensaje","DELET ERROR");
        return "redirect:/VerMovimoentos";
    }

}
