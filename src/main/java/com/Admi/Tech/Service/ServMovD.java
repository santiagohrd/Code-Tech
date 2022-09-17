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

    public List<MovDin> getAllMov() {
        List<MovDin> movList = new ArrayList<>();
        repoMovD.findAll().forEach(mov -> movList.add(mov));
        return movList;
    }
    //retorna objeto tipo empresa desde el id
    public MovDin getMovID(Integer id){
        return repoMovD.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public boolean saOrUpMov(MovDin movDi){
        MovDin movD= repoMovD.save(movDi);
        if(repoMovD.findById(movD.getId()) !=null){
            return true;
        }
        return false;
    }

    //Eleminar
    public boolean deleMov(Integer id){
        repoMovD.deleteById(id);
        if(repoMovD.findById(id) != null){
            return true;
        }
        return false;
    }
    //metodo para obtener por el id del empleado
    public  ArrayList<MovDin> obPorEmple(Integer id){
        return  this.repoMovD.findByEmple(id);
    }

    public ArrayList<MovDin> obPorEmpre(Integer id){
        return this.repoMovD.findByEmpre(id);
    }


}
