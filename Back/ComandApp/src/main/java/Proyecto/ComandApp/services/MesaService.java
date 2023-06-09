package Proyecto.ComandApp.services;

import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.MesaRest;
import Proyecto.ComandApp.json.ProductoRest;

import java.util.List;

public interface MesaService {

    MesaRest getMesaById (Long mesaId) throws ComandAppException;
    MesaRest cobrarMesa(Long mesaId) throws ComandAppException;
    MesaRest borrarComandas(Long mesaId) throws ComandAppException;
    MesaRest comandar(Long mesaId, List<ProductoRest> productosRest) throws ComandAppException;
    List<MesaRest> getMesas()throws ComandAppException;
}
