package org.example;

import org.example.controller.PricesController;
import org.example.dto.PricesDto;
import org.example.entity.BrandEntity;
import org.example.entity.PriceEntity;
import org.example.entity.ProductEntity;
import org.example.enums.Currency;
import org.example.service.PriceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PricesController.class})
public class PricesControllerTest {

    @MockBean
    @Qualifier("Prices")
    private PriceService priceService;
    private static final BrandEntity brand= new BrandEntity(1L, "ZARA", "ZARA", null);
    private static final ProductEntity product = new ProductEntity(35455L, "Short", "Zara short", null);
    private static final List<PriceEntity> entities = Arrays.asList(
            new PriceEntity(1L, new Timestamp(2020, 6, 14, 0, 0, 0, 0),
                    new Timestamp(2020, 12, 31, 23, 59, 59, 0), 0L, new BigDecimal("35.5"), Currency.EUR, brand, product),
            new PriceEntity(2L, new Timestamp(2020, 6, 14, 15, 0, 0, 0),
                    new Timestamp(2020, 6, 14, 18, 30, 0, 0), 1L, new BigDecimal("25.45"), Currency.EUR, brand, product),
            new PriceEntity(3L, new Timestamp(2020, 6, 15, 0, 0, 0, 0),
                    new Timestamp(2020, 6, 15, 11, 0, 0, 0), 1L, new BigDecimal("30.5"), Currency.EUR, brand, product),
            new PriceEntity(4L, new Timestamp(2020, 6, 15, 16, 0, 0, 0),
                    new Timestamp(2020, 12, 31, 23, 59, 59, 0), 1L, new BigDecimal("38.95"), Currency.EUR, brand, product)
    );

    @Autowired
    private PricesController pricesController;

    @Test
    public void shouldReturnTo14at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceService.getPriceRepository().findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 3);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
        Assert.assertEquals(response.get(2).getStartDate(), date.toString());
    }

    @Test
    public void shouldReturnTo14at16OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceService.getPriceRepository().findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 3);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
        Assert.assertEquals(response.get(2).getStartDate(), date.toString());
    }

    @Test
    public void shouldReturnTo14at21OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceService.getPriceRepository().findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 3);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
        Assert.assertEquals(response.get(2).getStartDate(), date.toString());
    }

    @Test
    public void shouldReturnTo15at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 15);
        Mockito.when(priceService.getPriceRepository().findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 3);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
        Assert.assertEquals(response.get(2).getStartDate(), date.toString());
    }

    private List<PriceEntity> mockPrices(Long brand, Long product, LocalDate date) {
        return entities.stream()
                .filter(entity -> entity.getBrand().getId().equals(brand) && entity.getProduct().getId().equals(product)) // TODO: filter date
                .collect(Collectors.toList());
    }
}
