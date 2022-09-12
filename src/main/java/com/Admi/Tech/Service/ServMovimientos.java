package com.Admi.Tech.Service;

import com.Admi.Tech.Modelo.MovimientoDinero;
import com.Admi.Tech.Repository.RepoMovimientos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ServMovimientos {
    public MovimientoDinero getById(int id);

    public int MovimientoDinero Update(int id, MovimientoDinero movimiento);
}
