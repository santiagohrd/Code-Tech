package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Repository.RepoEmpre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServEmpre {
    @Autowired //autoconectese al repositorio factory y hereda
    RepoEmpre repoEmpre;
    //retorna la lista de empresas
    public List<Empresa> getAllEmpresas(){
        List<Empresa> empreyList = new ArrayList<>();
        repoEmpre.findAll().forEach(empresa -> empreyList.add(empresa));
        return  empreyList;
    }
    //retorna objeto tipo empresa desde el id
    public Empresa getEmpreID(Integer id){
        return repoEmpre.findById(id).get();
    }

    //actualizacion o guardar objeto tipo empresa
    public Empresa saOrUpEmpre(Empresa empresa){
        Empresa empre= repoEmpre.save(empresa);
        return empre;
    }

    //Eleminar
    public boolean deleEmpre(Integer id){
        repoEmpre.deleteById(id);
        if(repoEmpre.findById(id) != null){
            return true;
        }
        return false;
    }

}
