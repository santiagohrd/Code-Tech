package com.Admi.Tech.Service;


import com.Admi.Tech.Modelo.MovDin;
import com.Admi.Tech.Repository.RepoMovD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServMovD {

    @Autowired
    RepoMovD repoMovD;

    public List<MovDin> getAllMov() { //Metodo que me muestra todos los movimientos sin ningn filtro
        List<MovDin> movList = new ArrayList<>();
        repoMovD.findAll().forEach(movimiento -> movList.add(movimiento));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return movList;
    }

    public MovDin getMovById(Integer id) { //Ver movimientos por id
        return repoMovD.findById(id).get();
    }

    public boolean saOrUpdateMov(MovDin movimientoDinero) { //Guardar o actualizar elementos
        MovDin mov = repoMovD.save(movimientoDinero);
        if (repoMovD.findById(mov.getId()) != null) {
            return true;
        }
        return false;
    }

    public boolean deleteMov(Integer id) { //Eliminar movimiento por id
        repoMovD.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if (this.repoMovD.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<MovDin> obtenerPorEmpleado(Integer id) { //Obterner teniendo en cuenta el id del empleado
        return repoMovD.findByEmple(id);
    }

    public ArrayList<MovDin> obPorEmpr(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
        return repoMovD.findByEmpre(id);
    }

    //Servicio para ver la suma de todos los montos
    public Long obSumaMon() {
        return repoMovD.SumarMonto();
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MonPorEmple(Integer id) {
        return repoMovD.MontosPorEmple(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MonPorEmpre(Integer id) {
        return repoMovD.MontosPorEmpre(id);
    }

    //servicio que nos deja conseguir el id de un empleado si tenemos su correo
    public Integer IdPorCor(String Correo) {
        return repoMovD.IdPorCorreo(Correo);
    }
}
