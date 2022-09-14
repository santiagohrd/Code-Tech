package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    ///me esta generando un error
    @PostMapping("/users") //Guardar un empleado nuevo
    public Optional<Empleado> guaEmpleado(@RequestBody Empleado emple){
        return Optional.ofNullable(this.servEmple.saOrUpEmple(emple));

    }

////////
    @GetMapping(path = "users/{id}")
    public Optional<Empleado> empleID(@PathVariable("id")Integer id){
        return this.servEmple.getEmpleID(id);
    }

    @GetMapping("/users/{id}/empleados")
    public ArrayList<Empleado> emplePorEmpre(@PathVariable("id") Integer id){
        return this.servEmple.obPorEmpre(id);
    }

    @PatchMapping("/user/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado emple = servEmple.getEmpleID(id).get();
        emple.setNombre(empleado.getNombre());
        emple.setCorreo(empleado.getCorreo());
        emple.setEmpresa(empleado.getEmpresa());
        emple.setRol(empleado.getRol());
        return servEmple.saOrUpEmple(emple);
    }
    @DeleteMapping(path = "/users/{id}")
    public String elimEmpleado(@PathVariable("id") Integer id) {
        boolean resp = this.servEmple.deleEmple(id);
        if (resp) {
            return "La eliminacion se realizo correctamente, Se elimino el id" + id;
        } else {
            return "L eliminacion no se realizo, No se elimino el id" + id;
        }
    }
}
