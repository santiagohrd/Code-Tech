package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovimientoDinero;
import com.Admi.Tech.Service.ServEmpre;
import com.Admi.Tech.Service.ServMov;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContMovimientos {

    @Autowired
    ServMov servMov;

    @GetMapping("/enterprises/{id}/movements")
    public List<MovimientoDinero> vermov() {

        return servMov.getAllMov();
    }
    @PostMapping("/enterprises/{id}/movements")
    public MovimientoDinero guardarMov(@RequestBody MovimientoDinero movimientoDinero){

        return this.servMov.saOrUpMov(movimientoDinero);
    }
    @GetMapping(path = "/enterprises/{id}/movements")
    public MovimientoDinero movID(@PathVariable("id")Integer id){

        return this.servMov.getMovID(id);
    }
    @PatchMapping("/enterprises/{id}/movements")
    public MovimientoDinero actualizarMov(@PathVariable("id")Integer id,@RequestBody Empresa empresa){
        MovimientoDinero mov= servMov.getMovID(id);
        mov.setConcepto(mov.getConcepto());
        mov.setCantidad(mov.getCantidad());
        mov.setUser(mov.getUser());
        return servMov.saOrUpMov(mov);
    }
    @DeleteMapping(path = "/enterprises/{id}/movements")
    public String elimMov(@PathVariable("id") Integer id) {
        boolean resp = this.servMov.deleMov(id);
        if (resp) {
            return "La eliminacion se realizo correctamente, Se elimino el id" + id;
        } else {
            return "L eliminacion no se realizo, No se elimino el id" + id;
        }
    }
}
