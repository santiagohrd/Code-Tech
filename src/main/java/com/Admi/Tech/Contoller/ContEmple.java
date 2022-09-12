package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContEmple {

    @Autowired
    ServEmple servEmple;

    @GetMapping("/users")
    public List<Empleado> verEmpleado(){
        return servEmple.getAllEmpleados();
    }

    @PostMapping("/users") //Guardar un empleado nuevo
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado emple){
        return Optional.ofNullable(this.servEmple.saOrUpEmple(emple));
    }


    @GetMapping(path = "users/{id}")
    public Optional<Empleado> empleID(@PathVariable("id")Integer id){
        return this.servEmple.getEmpleID(id);
    }
    @PatchMapping("user/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl=servEmple.getEmpleID(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return servEmple.saOrUpEmple(empl);
    }
    @DeleteMapping(path = "users/{id}")
    public String elimEmpleado(@PathVariable("id") Integer id) {
        boolean resp = this.servEmple.deleEmple(id);
        if (resp) {
            return "La eliminacion se realizo correctamente, Se elimino el id" + id;
        } else {
            return "L eliminacion no se realizo, No se elimino el id" + id;
        }
    }




}
