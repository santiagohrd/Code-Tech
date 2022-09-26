package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServ {

    @Autowired
    EmpresaRepo empresaRepo;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepo.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Empresa getEmpresaById(Integer id){
        return  empresaRepo.findById(id).get();
    }

    public boolean savOrUpEmpresa(Empresa empresa){
        Empresa empre= empresaRepo.save(empresa);
        if(empresaRepo.findById(empre.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean delEmpresa(Integer id){
        empresaRepo.deleteById(id);
        if(empresaRepo.findById(id) != null){
            return true;
        }
        return false;
    }
}
