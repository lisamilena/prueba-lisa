package org.example;

import ma.glasnost.orika.MapperFacade;
import org.example.controller.PricesController;
import org.example.dto.PricesDto;
import org.example.entity.BrandEntity;
import org.example.entity.PriceEntity;
import org.example.entity.ProductEntity;
import org.example.enums.Currency;
import org.example.repository.PriceRepository;
import org.example.service.PriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PricesController.class})
public class PricesControllerTest {

    @MockBean
    private PriceService priceService;

    @MockBean
    private PriceRepository priceRepository;

    private static final BrandEntity brand= new BrandEntity(1L, "ZARA", "ZARA", null);
    private static final ProductEntity product = new ProductEntity(35455L, "Short", "Zara short", null);
    private static final List<PriceEntity> entities = Arrays.asList( //'2011-12-03T10:15:30'
            new PriceEntity(1L, LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59, 59), 0L, new BigDecimal("35.5"), Currency.EUR, brand, product),
            new PriceEntity(2L, LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                    LocalDateTime.of(2020, 6, 14, 18, 30, 0), 1L, new BigDecimal("25.45"), Currency.EUR, brand, product),
            new PriceEntity(3L, LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                    LocalDateTime.of(2020, 6, 15, 11, 0, 0), 1L, new BigDecimal("30.5"), Currency.EUR, brand, product),
            new PriceEntity(4L, LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, new BigDecimal("38.95"), Currency.EUR, brand, product)
    );

    @Autowired
    private PricesController pricesController;

    @Before
    public void setUp() {
        priceService = Mockito.mock(PriceService.class);
        MapperFacade mapper = Mockito.mock(MapperFacade.class);
        priceRepository = Mockito.mock(PriceRepository.class);
        this.pricesController = new PricesController(priceService, mapper);
    }

    @Test
    public void shouldReturnTo14at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceRepository.findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    @Test
    public void shouldReturnTo14at16OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceRepository.findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    @Test
    public void shouldReturnTo14at21OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(priceRepository.findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
        Assert.assertEquals(response.get(2).getStartDate(), date.toString());
    }

    @Test
    public void shouldReturnTo15at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 15);
        Mockito.when(priceRepository.findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 3);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    @Test
    public void shouldReturnTo16at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 15);
        Mockito.when(priceRepository.findByParams(brand, product, date.toString(), date.toString()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    private List<PriceEntity> mockPrices(Long brand, Long product, LocalDate date) {
        return entities.stream()
                .filter(entity -> entity.getBrand().getId().equals(brand) &&
                        entity.getProduct().getId().equals(product) &&
                        entity.getStartdate().isBefore(date.plusDays(1).atStartOfDay())
                        && entity.getEnddate().isAfter(date.atStartOfDay()))
                .collect(Collectors.toList());
    }
}
