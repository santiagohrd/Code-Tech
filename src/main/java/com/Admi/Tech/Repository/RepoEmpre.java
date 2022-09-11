package com.Admi.Tech.Repository;

import com.Admi.Tech.Modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoEmpre extends JpaRepository<Empresa,Integer> {

}
