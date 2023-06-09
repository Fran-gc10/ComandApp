package Proyecto.ComandApp.controllers.impl;

import Proyecto.ComandApp.controllers.MesaController;
import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.json.MesaRest;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.responses.ComandAppResponse;
import Proyecto.ComandApp.services.MesaService;
import Proyecto.ComandApp.utils.constants.CommonConstants;
import Proyecto.ComandApp.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.MESA)
public class MesaControllerImpl implements MesaController {

    @Autowired
    MesaService mesaService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.PARAMETER_MESA_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<MesaRest> getMesaById(@PathVariable Long mesaId) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                mesaService.getMesaById(mesaId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<List<MesaRest>> getMesas() throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                mesaService.getMesas());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.COBRAR_MESA, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<MesaRest> cobrarMesa(@PathVariable Long mesaId) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                mesaService.cobrarMesa(mesaId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.BORRAR_COMANDAS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<MesaRest> borrarComandas(@PathVariable Long mesaId) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                mesaService.borrarComandas(mesaId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.COMANDAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ComandAppResponse<MesaRest> comandar(@PathVariable Long mesaId, @RequestBody List<ProductoRest> productosRest) throws ComandAppException {
        return new ComandAppResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                mesaService.comandar(mesaId, productosRest));
    }
}