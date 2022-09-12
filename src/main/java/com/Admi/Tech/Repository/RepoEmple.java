package com.Admi.Tech.Repository;

import com.Admi.Tech.Modelo.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

//public interface RepoEmple extends JpaRepository<Empleado, Integer>
@Repository
public interface RepoEmple extends CrudRepository<Empleado, Integer> {
@Query("select e from Empleado e where e.empresa = ?1")
public abstract ArrayList<Empleado> findByEmpresa(Integer id);


}
