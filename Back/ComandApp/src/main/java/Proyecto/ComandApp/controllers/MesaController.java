package Proyecto.ComandApp.controllers;

import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.MesaRest;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.responses.ComandAppResponse;
import java.util.List;

public interface MesaController {

    ComandAppResponse<MesaRest> getMesaById (Long mesaId) throws ComandAppException;
    ComandAppResponse<List<MesaRest>> getMesas()throws ComandAppException;
    ComandAppResponse<MesaRest> cobrarMesa (Long mesaId) throws ComandAppException;
    ComandAppResponse<MesaRest> borrarComandas (Long mesaId) throws ComandAppException;
    ComandAppResponse<MesaRest> comandar (Long mesaId, List<ProductoRest> productosRest) throws ComandAppException;
}
