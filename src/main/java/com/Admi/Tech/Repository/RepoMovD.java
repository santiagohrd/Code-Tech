package com.Admi.Tech.Repository;

import com.Admi.Tech.Modelo.MovDin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepoMovD extends JpaRepository< MovDin, Integer> {
    @Query(value = "select * from MovDinero where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovDin> findByEmple(Integer id);

    //filtro por empresa
    @Query(value = "select * from MovDinero where empleado_id in (select id from empleado where empresa_id = ?1)", nativeQuery = true)
    public  abstract ArrayList<MovDin> findByEmpre(Integer id);
}
