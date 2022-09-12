package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empleado;
import com.Admi.Tech.Repository.RepoEmple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServEmple  {

    @Autowired
    RepoEmple repoEmple;

    public List<Empleado> getAllEmpleados(){
        List<Empleado> empleList = new ArrayList<>();
        repoEmple.findAll().forEach(empleado -> empleList.add(empleado));
        return  empleList;
    }


    //metodo para buscar empleado por ID con un metodo optional
    public Optional<Empleado> getEmpleID(Integer id){

       return repoEmple.findById(id);

    }
    //buscar empleado por empresa
    public ArrayList<Empleado> obPorEmpre(Integer id){
        return repoEmple.findByEmpresa(id);
    }

    //actualizacion o guardar objeto
    public Empleado saOrUpEmple(Empleado empleado){
        return repoEmple.save(empleado);
    }

    //Eleminar
    public boolean deleEmple(Integer id){
        repoEmple.deleteById(id);
        if(repoEmple.findById(id) != null){
            return true;
        }
        return false;
    }
}
