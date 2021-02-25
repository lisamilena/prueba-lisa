package org.example.entity;

import org.example.enums.Currency;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp startdate;
    private Timestamp enddate;
    private Long priority;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Currency currency;

//    @ManyToOne
//    @JoinColumn(name = "BRAND_ID", referencedColumnName = "brandid")
//    private BrandEntity brand;
//
//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productid")
//    private ProductEntity product;
}
