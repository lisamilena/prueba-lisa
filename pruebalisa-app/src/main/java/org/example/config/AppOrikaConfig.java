package org.example.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppOrikaConfig {

    @Bean("appOrika")
    public MapperFacade buildMapperFacade() {
        DefaultMapperFactory mapper = new DefaultMapperFactory.Builder().build();

        return mapper.getMapperFacade();
    }
}