package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Repository.RepoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServFactory {
    @Autowired //autoconectese al repositorio factory y hereda
    RepoFactory repoFactory;
    //retorna la lista de empresas
    public List<Empresa> getAllEmpresas(){
        List<Empresa> factoryList = new ArrayList<>();
        repoFactory.findAll().forEach(empresa -> factoryList.add(empresa));
        return  factoryList;
    }
    //retorna objeto tipo empresa desde el id
    public Empresa getFactoryID(Integer id){
        return repoFactory.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public boolean saOrUpFactory(Empresa empresa){
        Empresa fac= repoFactory.save(empresa);
        if(repoFactory.findById(fac.getId()) !=null){
            return true;
        }
        return false;
    }

    //Eleminar

}
