package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PriceService;

@RestController
@RequestMapping("price")
public class PricesController {

    private final PriceService priceService;

    @Autowired
    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }
}