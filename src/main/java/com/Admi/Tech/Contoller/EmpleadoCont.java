package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
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
public class EmpleadoCont {
    @Autowired
    EmpleadoServ empleadoServ;

    @Autowired
    EmpresaServ empresaServ;

    @GetMapping({"/VerEmpleados"})
    public String viewEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empleado> listaEmpleado = empleadoServ.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleado);
        model.addAttribute("mensaje", mensaje);
        return "ver_Empleados";
    }

    @GetMapping("/AgregarEmpleado")
    public String newEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado empl = new Empleado();
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaServ.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "agregar_Empleado";
    }

    @PostMapping("/GuardarEmpleado")
    public String saveEmpresa(Empleado empl, RedirectAttributes redirectAttributes){
        if ((empleadoServ.saOrUpEmpleado(empl) == true)){
            redirectAttributes.addFlashAttribute("mensaje","SAVE OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","SAVE ERROR");
        return "redirect:/AgregarEmpleado";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public  String editEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado empl = empleadoServ.getEmpleadoById(id).get();
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaServ.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "editar_Empleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String actEmpleado(@ModelAttribute("empl") Empleado empl, RedirectAttributes redirectAttributes){
        if (empleadoServ.saOrUpEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje","UPDATE OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","UPDATE ERROR");
        return "redirect:/EditarEmpleado" + empl.getId();
    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliEmpleado(@PathVariable Integer id , RedirectAttributes redirectAttributes){
        if (empleadoServ.deleEmpleado(id)){
            redirectAttributes.addFlashAttribute("mensaje","DELET OK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","DELET ERROR");
        return "redirect:/VerEmpleados";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public  String verEmplePorEmpre(@PathVariable("id")Integer id, Model model){
        List<Empleado> listEmple = empleadoServ.obtenerPorEmpresa(id);
        model.addAttribute("emplelist", listEmple);
        return "ver_Empleados";
    }
}

