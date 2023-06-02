package Proyecto.ComandApp.services.impl;

import Proyecto.ComandApp.entities.Producto;
import Proyecto.ComandApp.exceptions.ComandAppException;
import Proyecto.ComandApp.exceptions.NotFoundException;
import Proyecto.ComandApp.json.ProductoRest;
import Proyecto.ComandApp.repositories.ProductoRepository;
import Proyecto.ComandApp.services.ProductoService;
import Proyecto.ComandApp.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ModelMapper modelMapper=new ModelMapper();

    private ProductoRepository prodRepository;


    @Override
    public List<ProductoRest> getProductos() throws ComandAppException {
        return prodRepository.findAll().stream().map(prod -> modelMapper.map(prod, ProductoRest.class)).collect(Collectors.toList());
    }

    @Override
    public ProductoRest getProdById(Long prodId) throws ComandAppException {
        try {
            return modelMapper.map(prodRepository.findById(prodId), ProductoRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ProductoRest createProd(ProductoRest prodRest) throws ComandAppException {
        Optional<Producto> optionalProd=prodRepository.findByNombre(prodRest.getNombre());
        if (optionalProd.isPresent()){
            throw new NotFoundException(ExceptionConstants.MESSAGE_EXISTENT_PROD);
        }
        Producto prod=Producto.builder().tipo(prodRest.getTipo()).nombre(prodRest.getNombre()).cantidad(prodRest.getCantidad()).precio(prodRest.getPrecio()).build();
        prodRepository.save(prod);
        return null;
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
        prod.setCantidad(prodRest.getCantidad());
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
