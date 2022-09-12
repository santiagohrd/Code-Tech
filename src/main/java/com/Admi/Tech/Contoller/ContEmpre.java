package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Service.ServEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ContEmpre {
    @Autowired
    ServEmpre servEmpre;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return servEmpre.getAllEmpresas();
    }
    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa empre){
        return this.servEmpre.saOrUpEmpre(empre);
    }
    @GetMapping(path = "enterprises/{id}")
    public Empresa empreID(@PathVariable("id")Integer id){
        return this.servEmpre.getEmpreID(id);
    }
    @PatchMapping("enterprises/{id}")
    public Empresa actualizarEmpre(@PathVariable("id")Integer id,@RequestBody Empresa empresa){
        Empresa empre = servEmpre.getEmpreID(id);
        empre.setNombre(empresa.getNombre());
        empre.setDireccion(empresa.getDireccion());
        empre.setTelefono(empresa.getTelefono());
        empre.setNIT(empresa.getNIT());
        return servEmpre.saOrUpEmpre(empre);
    }
    @DeleteMapping(path = "enterprises/{id}")
    public String elimEmpresa(@PathVariable("id") Integer id) {
        boolean resp = this.servEmpre.deleEmpre(id);
        if (resp) {
            return "La eliminacion se realizo correctamente, Se elimino el id" + id;
        } else {
            return "L eliminacion no se realizo, No se elimino el id" + id;
        }
    }

}
