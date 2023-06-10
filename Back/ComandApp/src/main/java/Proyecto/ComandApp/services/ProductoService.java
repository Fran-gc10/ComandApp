package Proyecto.ComandApp.services;

import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.ProductoRest;

import java.util.List;

public interface ProductoService {

    ProductoRest getProdById (Long prodId) throws ComandAppException;
    ProductoRest createProd(ProductoRest prodRest)throws ComandAppException;
    ProductoRest updateProd(ProductoRest prodRest)throws ComandAppException;
    ProductoRest deleteProdById (Long prodId) throws ComandAppException;
    List<ProductoRest> getProductos()throws ComandAppException;
    List<String> getTipos()throws ComandAppException;
    List<ProductoRest> getProdByTipo(String tipo)throws ComandAppException;
}
