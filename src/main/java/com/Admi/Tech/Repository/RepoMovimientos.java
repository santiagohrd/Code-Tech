package com.Admi.Tech.Repository;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepoMovimientos extends JpaRepository<MovimientoDinero, Integer> {
   //@Query(value = "select * from Empresa where Empleado_id = ?1", nativeQuery = true)
   //public abstract List<Empresa> findById(int id);

}
