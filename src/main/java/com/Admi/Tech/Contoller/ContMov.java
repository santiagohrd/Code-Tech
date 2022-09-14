package com.Admi.Tech.Contoller;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovDinero;
import com.Admi.Tech.Service.ServMov;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContMov {

    @Autowired
    ServMov servMov;

    @GetMapping("/movements")
    public List<MovDinero> verMov(){

        return servMov.getAllMov();
    }
    @PostMapping("/movements")
    public MovDinero guardarMov(@RequestBody MovDinero mov){
        return this.servMov.saOrUpMov(mov);
    }

    @GetMapping(path = "/enterprisess/{id}")
    public MovDinero movID(@PathVariable("id")Integer id){
        return this.servMov.getMovID(id);
    }

    @PatchMapping("/enterprisess/{id}/movements")//Editar o actualizar un movimiento
    public MovDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovDinero movDinero){
        MovDinero mov=servMov.getMovID(id);
        mov.setConcepto(movDinero.getConcepto());
        mov.setMonto(movDinero.getMonto());
        mov.setUsuario(movDinero.getUsuario());
        return servMov.saOrUpMov(mov);
    }

    @DeleteMapping("/enterprises/{id}/movements")
    public String deleteMovimiento(@PathVariable("id") Integer id){
        boolean respuesta= servMov.deleMov(id);
        if (respuesta){
            return "Se elimino correctamente el movimiento con id " +id;
        }
        return "No se pudo eliminar el movimiento con id "+id;
    }

    @GetMapping("/enterprises/{id}/movementss") //Consultar movimientos por id del empleado
    public ArrayList<MovDinero> movimientosPorEmpleado(@PathVariable("id") Integer id){
        return servMov.obPorEmple(id);
    }

    @GetMapping("/enterprises/[id]/movementst") //Consultar movimientos que pertenecen a una empresa por el id de la empresa
    public ArrayList<MovDinero> movimientosPorEmpresa(@PathVariable("id") Integer id){
        return servMov.obPorEmpre(id);
    }


}
