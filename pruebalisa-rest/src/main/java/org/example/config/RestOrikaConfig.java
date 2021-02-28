package org.example.config;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.dto.PricesDto;
import org.example.model.PriceModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;

@Configuration
public class RestOrikaConfig {

    @Bean("restOrika")
    public MapperFacade buildMapperFacade() {
        DefaultMapperFactory mapper = new DefaultMapperFactory.Builder().build();

        mapper.classMap(PriceModel.class, PricesDto.class)
        .customize(new CustomMapper<>() {
            @Override
            public void mapAtoB(PriceModel priceModel, PricesDto pricesDto, MappingContext context) {
                pricesDto.setFareId(priceModel.getId());
                pricesDto.setBrandId(priceModel.getBrand() != null ?
                        priceModel.getBrand().getId() : null);
                pricesDto.setProductId(priceModel.getProduct() != null ?
                        priceModel.getProduct().getId() : null);
                pricesDto.setStartDate(formatDate(priceModel.getStartdate()));
                pricesDto.setEndDate(formatDate(priceModel.getEnddate()));
            }

            private String formatDate(LocalDateTime date) {
                return date != null ? date.toString() : null;
            }
        })
        .byDefault()
        .register();

        return mapper.getMapperFacade();
    }
}
