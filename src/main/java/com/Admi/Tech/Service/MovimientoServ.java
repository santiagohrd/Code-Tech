package com.Admi.Tech.Service;


import com.Admi.Tech.Modelo.MovimientoDinero;
import com.Admi.Tech.Repository.MovimientoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoServ{
    @Autowired
    MovimientoRepo movimientoRepo;

    public List<MovimientoDinero> getAllMovimientos(){ //Metodo que me muestra todos los movimientos sin ningn filtro
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientoRepo.findAll().forEach(movimiento -> movimientosList.add(movimiento));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id){ //Ver movimientos por id
        return movimientoRepo.findById(id).get();
    }

    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero){ //Guardar o actualizar elementos
        MovimientoDinero mov=movimientoRepo.save(movimientoDinero);
        if (movimientoRepo.findById(mov.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientoRepo.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientoRepo.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) { //Obterner teniendo en cuenta el id del empleado
        return movimientoRepo.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
        return movimientoRepo.findByEmpresa(id);
    }
    //Servicio para ver la suma de todos los montos
    public Long obSumaMon() {
        return movimientoRepo.SumarMonto();
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MonPorEmple(Integer id) {
        return movimientoRepo.MontosPorEmple(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MonPorEmpre(Integer id) {
        return movimientoRepo.MontosPorEmpre(id);
    }

    //servicio que nos deja conseguir el id de un empleado si tenemos su correo
    public Integer IdPorCor(String Correo) {
        return movimientoRepo.IdPorCorreo(Correo);
    }
}
