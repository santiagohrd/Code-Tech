package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.MovDin;
import com.Admi.Tech.Repository.RepoMovD;
import com.Admi.Tech.Service.ServEmple;
import com.Admi.Tech.Service.ServMovD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContMovD {

        @Autowired
        ServMovD servMovD;
        @Autowired
        ServEmple servEmple;
        
    @RequestMapping("/VerMovimientos")// Controlador que nos lleva al template donde veremos todos los movimientos
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovDin> movlist=servMovD.getAllMov();
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto=servMovD.obSumaMon();
        model.addAttribute("SumaMontos",sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento") //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovDin movimiento= new MovDin();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        Integer idEmpleado=servMovD.IdPorCor(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "agregarMovimiento"; //Llamar HTML
    }

    @PostMapping("/GuardarMovimiento")
    public String guaMov(MovDin mov, RedirectAttributes redirectAttributes){
        if(servMovD.saOrUpdateMov(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovDin mov=servMovD.getMovById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados= servEmple.getAllEmpleados();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovDin mov, RedirectAttributes redirectAttributes){
        if(servMovD.saOrUpdateMov(mov)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiemto/"+mov.getId();

    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (servMovD.deleteMov(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimientos";
    }

    @GetMapping("/Empleado/{id}/Movimientos") //Filtro de movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id")Integer id, Model model){
        List<MovDin> movlist = servMovD.obtenerPorEmpleado(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=servMovD.MonPorEmple(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/Empresa/{id}/Movimientos") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id")Integer id, Model model){
        List<MovDin> movlist = servMovD.obPorEmpr(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=servMovD.MonPorEmpre(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }

    //Controlador que me lleva al template de No autorizado
    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "accessDenied";
    }

}