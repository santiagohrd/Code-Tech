package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.MovDinero;
import com.Admi.Tech.Repository.RepoMovDi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServMov {

    @Autowired
    RepoMovDi repoMovDi;

    public List<MovDinero> getAllMov() {
        List<MovDinero> movList = new ArrayList<>();
        repoMovDi.findAll().forEach(mov -> movList.add(mov));
        return movList;
    }
    //retorna objeto tipo empresa desde el id
    public MovDinero getMovID(Integer id){
        return repoMovDi.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public MovDinero saOrUpMov(MovDinero movDinero){
        MovDinero mov= repoMovDi.save(movDinero);
        return mov;
    }

    //Eleminar
    public boolean deleMov(Integer id){
        repoMovDi.deleteById(id);
        if(repoMovDi.findById(id) != null){
            return true;
        }
        return false;
    }
    //metodo para obtener por el id del empleado
    public  ArrayList<MovDinero> obPorEmple(Integer id){
        return  this.repoMovDi.findByEmple(id);
    }

    public ArrayList<MovDinero> obPorEmpre(Integer id){
        return this.repoMovDi.findByEmpre(id);
    }

    }
