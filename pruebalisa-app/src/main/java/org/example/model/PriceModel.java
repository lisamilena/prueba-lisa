package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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