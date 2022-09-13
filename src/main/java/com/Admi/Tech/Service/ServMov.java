package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovimientoDinero;
import com.Admi.Tech.Repository.RepoMovimientos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServMov {

    @Autowired
    RepoMovimientos repoMovimientos;

    public List<MovimientoDinero> getAllMov() {
        List<MovimientoDinero> movList = new ArrayList<>();
        repoMovimientos.findAll().forEach(mov -> movList.add(mov));
        return movList;
    }
    //retorna objeto tipo empresa desde el id
    public MovimientoDinero getMovID(Integer id){
        return repoMovimientos.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public MovimientoDinero saOrUpMov(MovimientoDinero movimientoDinero){
        MovimientoDinero mov= repoMovimientos.save(movimientoDinero);
        return mov;
    }

    //Eleminar
    public boolean deleMov(Integer id){
        repoMovimientos.deleteById(id);
        if(repoMovimientos.findById(id) != null){
            return true;
        }
        return false;
    }

    }
