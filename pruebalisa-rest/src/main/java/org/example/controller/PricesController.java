package org.example.controller;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import org.example.dto.PricesDto;
import org.example.exception.ServiceException;
import org.example.exceptions.ApiException;
import org.example.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@Getter
public class PricesController {

    private final PriceService priceService;
    private final MapperFacade mapper;

    @Autowired
    public PricesController(PriceService priceService, @Qualifier("restOrika") MapperFacade mapper) {
        this.priceService = priceService;
        this.mapper = mapper;
    }

    @GetMapping("prices")
    public List<PricesDto> findPrices(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date,
                                      @RequestParam(required = false) Long productId,
                                      @RequestParam(required = false) Long brandId) {
        try {
            return mapper.mapAsList(priceService.findPrices(date, productId, brandId), PricesDto.class);
        } catch (ServiceException e) {
            throw new ApiException(e.getType());
        } catch (Exception e) {
            throw new ApiException("Error", e);
        }
    }
}