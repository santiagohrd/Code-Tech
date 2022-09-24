package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Repository.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServ {

    @Autowired
    EmpleadoRepo empleadoRepo;

    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepo.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    public Optional<Empleado> getEmpleadoById(Integer id){
        return empleadoRepo.findById(id);
    }

    //buscar empleado por empresa
    //Metodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepo.findByEmpresa(id);
    }

    //actualizacion o guardar objeto
    public boolean saOrUpEmpleado(Empleado empleado){
        Empleado empl= empleadoRepo.save(empleado);
        if(empleadoRepo.findById(empl.getId()) !=null){
            return true;
        }
        return false;
    }

    //Eleminar
    public boolean deleEmpleado(Integer id){
        empleadoRepo.deleteById(id);
        if(this.empleadoRepo.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
