package org.example.service;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import org.example.exception.ExceptionType;
import org.example.exception.ServiceException;
import org.example.model.PriceModel;
import org.example.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service("Prices")
@Getter
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
            return mapper.mapAsList(priceRepository.findByParams(getPrimitiveLong(brandId),
                    getPrimitiveLong(productId),
                    getFormattedDate(date, 1),
                    getFormattedDate(date, 0)), PriceModel.class);
        } catch (Exception e) {
            throw new ServiceException(ExceptionType.ERROR_PRICES.getCode(), e.getMessage());
        }
    }

    private long getPrimitiveLong(Long number) {
        return number != null ? number : 0;
    }

    private String getFormattedDate(LocalDate date, long plusDays) {
        return date != null ? date.plusDays(plusDays).toString() : "null";
    }
}