package Proyecto.ComandApp.controllers.impl;

import Proyecto.ComandApp.controllers.ProductoController;
import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.responses.ComandAppResponse;
import Proyecto.ComandApp.services.ProductoService;
import Proyecto.ComandApp.utils.constants.CommonConstants;
import Proyecto.ComandApp.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(RestConstants.PRODUCTO)
public class ProductoControllerImpl implements ProductoController {

    @Autowired
    ProductoService productoService;


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.PARAMETER_PRODUCTO_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<ProductoRest> getProdById(@PathVariable Long prodId) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
        productoService.getProdById(prodId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<ProductoRest> createProd(@RequestBody ProductoRest prodRest) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.createProd(prodRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<ProductoRest> updateProd(@RequestBody ProductoRest prodRest) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.updateProd(prodRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.PARAMETER_PRODUCTO_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<ProductoRest> deleteProdById(@PathVariable Long prodId) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.deleteProdById(prodId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<List<ProductoRest>> getProductos() throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.getProductos());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.GET_TIPOS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<List<String>> getTipos() throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.getTipos());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.PROD_BY_TIPO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<List<ProductoRest>> getProdByTipo(@PathVariable String tipo) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                productoService.getProdByTipo(tipo));
    }
}
