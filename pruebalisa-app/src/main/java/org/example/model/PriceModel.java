package org.example.model;

import lombok.Data;
import org.example.enums.Currency;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PriceModel {

    private Long id;
    private Timestamp startdate;
    private Timestamp enddate;
    private Long priority;
    private BigDecimal price;
    private Currency currency;

    private BrandModel brand;
    private ProductModel product;
}