package Proyecto.ComandApp.services.impl;

import Proyecto.ComandApp.entities.Producto;
import Proyecto.ComandApp.enums.TipoProd;
import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.exceptions.NotFoundException;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.repositories.ProductoRepository;
import Proyecto.ComandApp.services.ProductoService;
import Proyecto.ComandApp.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ModelMapper modelMapper=new ModelMapper();

    @Autowired
    private ProductoRepository prodRepository;


    @Override
    public List<ProductoRest> getProductos() throws ComandAppException {
        return prodRepository.findAll().stream().map(prod -> modelMapper.map(prod, ProductoRest.class)).collect(Collectors.toList());
    }

    @Override
    public List<String> getTipos() throws ComandAppException {
        List<String> listaTipos=prodRepository.findAll().stream()
                .map(Producto::getTipo).distinct()
                .map(tipoProd -> modelMapper.map(tipoProd,String.class)).collect(Collectors.toList());
        return listaTipos;
    }

    @Override
    public List<ProductoRest> getProdByTipo(String tipo) throws ComandAppException {
        return prodRepository.findByTipo(TipoProd.valueOf(tipo)).stream().map(prod->modelMapper.map(prod, ProductoRest.class)).collect(Collectors.toList());
    }

    @Override
    public ProductoRest getProdById(Long prodId) throws ComandAppException {
        Producto prod=prodRepository.findById(prodId).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROD));
        return modelMapper.map(prod, ProductoRest.class);
    }

    @Override
    public ProductoRest createProd(ProductoRest prodRest) throws ComandAppException {
        Optional<Producto> optionalProd=prodRepository.findByNombre(prodRest.getNombre());
        if (optionalProd.isPresent()){
            throw new NotFoundException(ExceptionConstants.MESSAGE_EXISTENT_PROD);
        }
        Producto prod=Producto.builder().tipo(prodRest.getTipo()).nombre(prodRest.getNombre()).precio(prodRest.getPrecio()).build();
        prodRepository.save(prod);
        return modelMapper.map(optionalProd,ProductoRest.class);
    }

    @Override
    public ProductoRest updateProd(ProductoRest prodRest) throws ComandAppException {
        Producto prod;
        try {
            prod= prodRepository.getOne(prodRest.getId());
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
        prod.setTipo(prodRest.getTipo());
        prod.setNombre(prodRest.getNombre());
        prod.setPrecio(prodRest.getPrecio());
        prodRepository.save(prod);

        return modelMapper.map(prod, ProductoRest.class);
    }

    @Override
    public ProductoRest deleteProdById(Long prodId) throws ComandAppException {
        Optional<Producto> optionalProd;
        try {
            optionalProd=prodRepository.findById(prodId);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
        prodRepository.deleteById(prodId);
        return modelMapper.map(optionalProd,ProductoRest.class);
    }



}
