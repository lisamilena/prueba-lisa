package org.example.dto;

import lombok.Data;

@Data
public class PricesDto {

    private Long productId;
    private Long brandId;
    private String fare;
    private String startDate;
    private String endDate;
    private Float price;
}