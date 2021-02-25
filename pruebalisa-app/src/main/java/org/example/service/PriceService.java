package org.example.service;

import ma.glasnost.orika.MapperFacade;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.model.PriceModel;
import org.example.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final MapperFacade mapper;

    @Autowired
    public PriceService(PriceRepository priceRepository, @Qualifier("appOrika") MapperFacade mapper) {
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }

    public List<PriceModel> findPrices(LocalDate date, Long productId, Long brandId) {
        try {
            return mapper.mapAsList(priceRepository.findAll(), PriceModel.class);
        } catch (Exception e) {
            throw new ServiceException(ExceptionType.ERROR_PRICES.getCode(), e.getMessage());
        }
    }
}