package Proyecto.ComandApp.controllers;

import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.responses.ComandAppResponse;
import java.util.List;

public interface ProductoController {
    ComandAppResponse<ProductoRest> getProdById (Long prodId) throws ComandAppException;
    ComandAppResponse<ProductoRest> createProd(ProductoRest prodRest)throws ComandAppException;
    ComandAppResponse<ProductoRest> updateProd(ProductoRest prodRest)throws ComandAppException;
    ComandAppResponse<ProductoRest> deleteProdById (Long prodId) throws ComandAppException;
    ComandAppResponse<List<ProductoRest>> getProductos()throws ComandAppException;
    ComandAppResponse<List<String>> getTipos()throws ComandAppException;
    ComandAppResponse<List<ProductoRest>> getProdByTipo(String tipo)throws ComandAppException;

}
