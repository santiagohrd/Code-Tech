package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.MovimientoDinero;
import com.Admi.Tech.Repository.MovimientoRepo;
import com.Admi.Tech.Service.EmpleadoServ;
import com.Admi.Tech.Service.EmpresaServ;
import com.Admi.Tech.Service.MovimientoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimientoCont {
    @Autowired
    MovimientoServ movimientoServ;
    @Autowired
    EmpleadoServ empleadoServ;
    @Autowired
    EmpresaServ empresaServ;
    @Autowired
    MovimientoRepo movimientoRepo;

    @GetMapping({"/VerMovimientos"})
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos = movimientoServ.getAllMovimientos();
        model.addAttribute("movlist",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto=movimientoServ.obSumaMon();
        model.addAttribute("SumaMontos",sumaMonto);
        return "ver_Movimientos";
    }
    @GetMapping("/AgregarMovimiento")
    public String newMovimiento(Model model, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movimiento = new MovimientoDinero();
        model.addAttribute("mov", movimiento);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoServ.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        return "agregar_Movimiento";
    }
    @PostMapping("/GuardarMovimiento")
    public String saveMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if ((movimientoServ.saveOrUpdateMovimiento(mov) == true)){
            redirectAttributes.addFlashAttribute("mensaje","SAVE OK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarMovimiento";
    }
    @GetMapping("/EditarMovimiento/{id}")
    public  String editMovi(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero mov = movimientoServ.getMovimientoById(id);
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleado = empleadoServ.getAllEmpleado();
        model.addAttribute("emprelist", listaEmpleado);
        return "editar_Movimiento";
    }
    @PostMapping("/ActualizarMovimiento")
    public String actMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if (movimientoServ.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","UPDATE OK");
            return "redirect:/VerMovimiento";
        }
        redirectAttributes.addFlashAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarMovimiento" + mov.getId();
    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliMovimiento(@PathVariable Integer id , RedirectAttributes redirectAttributes) {
        if (movimientoServ.deleteMovimiento(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "DELET OK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "DELET ERROR");
        return "redirect:/VerMovimientos";
    }

    @GetMapping("/Empleado/{id}/Movimientos")
    public String moviPorEmpleado(@PathVariable("id") Integer id, Model model){
        List<MovimientoDinero>movlist = movimientoServ.obtenerPorEmpleado(id);
        model.addAttribute("movlist", movlist);
        Long sumaMonto=movimientoServ.MonPorEmple(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "ver_Movimientos";

    }

    @GetMapping("/Empresa/{id}/Movimiento") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id")Integer id, Model model) {
        List<MovimientoDinero> movlist = movimientoServ.obtenerPorEmpresa(id);
        model.addAttribute("movlist", movlist);
        Long sumaMonto=movimientoServ.MonPorEmpre(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "ver_Movimientos"; //Llamamos al HTML
    }

}
