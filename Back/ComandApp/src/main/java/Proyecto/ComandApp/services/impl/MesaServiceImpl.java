package Proyecto.ComandApp.services.impl;

import Proyecto.ComandApp.entities.Mesa;
import Proyecto.ComandApp.entities.Producto;
import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.exceptions.NotFoundException;
import Proyecto.ComandApp.json.MesaRest;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.repositories.MesaRepository;
import Proyecto.ComandApp.services.MesaService;
import Proyecto.ComandApp.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaServiceImpl implements MesaService {

    private final ModelMapper modelMapper=new ModelMapper();

    @Autowired
    private MesaRepository mesaRepository;


    @Override
    public MesaRest getMesaById(Long mesaId) throws ComandAppException {
        Mesa mesa=mesaRepository.findById(mesaId).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROD));
        return modelMapper.map(mesa,MesaRest.class);
    }

    @Override
    public MesaRest cobrarMesa(Long mesaId) throws ComandAppException {
        Mesa mesa=mesaRepository.findById(mesaId).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROD));
        List<Producto> listaProd =mesa.getProductos();
        listaProd.clear();
        mesa.setProductos(listaProd);
        mesaRepository.save(mesa);
        return modelMapper.map(mesa,MesaRest.class);
    }

    @Override
    public MesaRest borrarComandas(Long mesaId) throws ComandAppException {
        Mesa mesa=mesaRepository.findById(mesaId).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROD));
        List<Producto> listaProd =mesa.getProductos();
        listaProd.clear();
        mesa.setProductos(listaProd);
        mesaRepository.save(mesa);
        return modelMapper.map(mesa,MesaRest.class);
    }

    @Override
    public MesaRest comandar(Long mesaId, List<ProductoRest> productosRest) throws ComandAppException {
        Mesa mesa=mesaRepository.findById(mesaId).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROD));
        List<Producto> listProd=productosRest.stream().map(prod->modelMapper.map(prod,Producto.class)).collect(Collectors.toList());
        List<Producto> listaProdOrigin=mesa.getProductos();
        listaProdOrigin.addAll(listProd);
        mesa.setProductos(listaProdOrigin);
        mesaRepository.save(mesa);
        return modelMapper.map(mesa,MesaRest.class);
    }

    @Override
    public List<MesaRest> getMesas() throws ComandAppException {
        return mesaRepository.findAll().stream().map(mesa -> modelMapper.map(mesa, MesaRest.class)).collect(Collectors.toList());
    }
}
