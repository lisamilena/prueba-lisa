package org.example.model;

import lombok.Data;
import org.example.enums.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceModel {

    private Long id;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private Long priority;
    private BigDecimal price;
    private Currency currency;

    private BrandModel brand;
    private ProductModel product;
}