package org.example;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.controller.PricesController;
import org.example.dto.PricesDto;
import org.example.entity.PriceEntity;
import org.example.enums.Currency;
import org.example.model.BrandModel;
import org.example.model.PriceModel;
import org.example.model.ProductModel;
import org.example.service.PriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class PricesControllerTest {

    @Mock
    PriceService priceService;

    @Autowired
    private PricesController pricesController;

    private static final BrandModel brand = new BrandModel(1L, "ZARA");
    private static final ProductModel product = new ProductModel(35455L, "Short");
    private static final List<PriceModel> entities = Arrays.asList(
            new PriceModel(1L, LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59, 59), 0L, new BigDecimal("35.5"), Currency.EUR, brand, product),
            new PriceModel(2L, LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                    LocalDateTime.of(2020, 6, 14, 18, 30, 0), 1L, new BigDecimal("25.45"), Currency.EUR, brand, product),
            new PriceModel(3L, LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                    LocalDateTime.of(2020, 6, 15, 11, 0, 0), 1L, new BigDecimal("30.5"), Currency.EUR, brand, product),
            new PriceModel(4L, LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                    LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, new BigDecimal("38.95"), Currency.EUR, brand, product)
    );

    @Before
    public void setUp() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(PriceModel.class, PricesDto.class)
                .customize(new CustomMapper<>() {
                    @Override
                    public void mapAtoB(PriceModel priceModel, PricesDto pricesDto, MappingContext context) {
                        pricesDto.setBrandId(priceModel.getBrand() != null ?
                                priceModel.getBrand().getId() : null);
                        pricesDto.setProductId(priceModel.getProduct() != null ?
                                priceModel.getProduct().getId() : null);
                    }
                })
                .byDefault()
                .register();
        mapperFactory.classMap(PriceEntity.class, PriceModel.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        pricesController = new PricesController(priceService, mapper);
    }

    @Test
    public void shouldReturnTo14at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 14);
        Mockito.when(pricesController.getPriceService().findPrices(any(), anyLong(), anyLong()))
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
        Mockito.when(pricesController.getPriceService().findPrices(any(), anyLong(), anyLong()))
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
        Mockito.when(pricesController.getPriceService().findPrices(any(), anyLong(), anyLong()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    @Test
    public void shouldReturnTo15at10OfProduct35455AndBrand1() {
        Long brand = 1L,
            product = 35455L;
        LocalDate date = LocalDate.of(2020, 6, 15);
        Mockito.when(pricesController.getPriceService().findPrices(any(), anyLong(), anyLong()))
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
        LocalDate date = LocalDate.of(2020, 6, 16);
        Mockito.when(pricesController.getPriceService().findPrices(any(), anyLong(), anyLong()))
                .thenReturn(mockPrices(brand, product, date));

        List<PricesDto> response = pricesController.findPrices(date, product, brand);
        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getBrandId(), brand);
        Assert.assertEquals(response.get(1).getProductId(), product);
    }

    private List<PriceModel> mockPrices(Long brand, Long product, LocalDate date) {
        return entities.stream()
                .filter(entity -> entity.getBrand().getId().equals(brand) &&
                        entity.getProduct().getId().equals(product) &&
                        entity.getStartdate().isBefore(date.plusDays(1).atStartOfDay())
                        && entity.getEnddate().isAfter(date.atStartOfDay()))
                .collect(Collectors.toList());
    }
}
