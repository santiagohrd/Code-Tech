package com.Admi.Tech.Repository;

import com.Admi.Tech.Modelo.Empresa;
import com.Admi.Tech.Modelo.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoMovimientos extends JpaRepository<MovimientoDinero, Integer> {
   @Query(value = "select * from Empresa where Empresa_id = ?1", nativeQuery = true)
   public abstract List<Empresa> findById(int id);

//   public abstract boolean saveBy(int id);

    // POST

    public abstract boolean saveByEnterprise ( Int id , MovimientoDinero movimientos ) ;
    @Query ( value = " insert into Transaction ( id , concept , amount , enterprise_id , employee_id ) values ( 21 , 22 , 23 , 24 , 25 ) " , nativeQuery - true )
    public abstract vold querySaveTransaction ( long id , String concept , float amount , long enterprise_id , long employee_id ) ;
    // PATCH
    public abstract Transaction patchByEnterprise ( long id , Transaction transaction ) ;
    @Query ( value = " update Transaction set id- ? 1 , concept - 72 , emount- ? 3 , enterprise_id = 74 , employee_id = 75 where enterprise_id = 76 " , nativeQuery =true)
            public abstract void queryPatchTransaction ( long id , String concept , float amount , long NewEnterprise_id , long employee_id , long enterprise_id)
// DELETE
    @Query ( value = " delete from Transaction where enterprise_id- ? 1 " , nativeQuery true )
            public abstract boolean deleteByEnterprise ( long id ) ;
}
