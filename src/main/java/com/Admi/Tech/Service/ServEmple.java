package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Repository.RepoEmple;
import com.Admi.Tech.Repository.RepoEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServEmple {

    @Autowired //autoconectese al repositorio factory y hereda
    RepoEmple repoEmple;
    //retorna la lista de empresas
    public List<Empleado> getAllEmpleados(){
        List<Empleado> empleList = new ArrayList<>();
        repoEmple.findAll().forEach(empleado -> empleList.add(empleado));
        return  empleList;
    }
    //retorna objeto tipo empresa desde el id
    public Empleado getEmpLeID(Integer id){
        return repoEmple.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public boolean saOrUpEmple(Empleado empleado){
        Empleado emple= repoEmple.save(empleado);
        if(repoEmple.findById(emple.getId()) !=null){
            return true;
        }
        return false;
    }

    //Eleminar
    public boolean deletEmple(Integer id){
        repoEmple.deleteById(id);
        if(getEmpLeID(id) != null){
            return false;
        }
        return true;
    }
}
