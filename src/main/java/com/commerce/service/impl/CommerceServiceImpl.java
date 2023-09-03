package com.commerce.service.impl;

import com.commerce.bean.PriceResponse;
import com.commerce.model.Prices;
import com.commerce.repository.PricesRepository;
import com.commerce.service.CommerceService;
import com.commerce.constants.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The commerce service implementation
 */
@Service
@AllArgsConstructor
public class CommerceServiceImpl implements CommerceService {

    @Autowired
    private PricesRepository pricesRepository;

    /**
     * Get price looking for product id, brand name and period valid
     *
     * @param productId the product id
     * @param brand the brand name
     * @param periodValid the period price is valid
     * @return the price response
     */
    @Override
    public PriceResponse getPrices(int productId, String brand, String periodValid) {
        try {
            Timestamp timestampValid = Timestamp.valueOf(periodValid);
            List<Prices> prices = pricesRepository.findPricesByProductIdBrandDate(productId, brand, timestampValid);
            if(prices.size()==1){
                return convertPriceToPriceResponse(prices.get(0), brand);
            } else if (prices.size()>1){
                return convertPriceToPriceResponse(prices.stream().max(Comparator.comparing(Prices::getPriority))
                        .orElseThrow(NoSuchElementException::new), brand);
            } else{
                throw new NoSuchElementException(Constants.PRICE_NOT_FOUND);
            }
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(Constants.TIMESTAMP_NOT_VALID);
        }
    }

    /**
     * Convert price entity to price response
     *
     * @param prices the price entity
     * @param brand the brand name
     * @return the price response
     */
    private PriceResponse convertPriceToPriceResponse(Prices prices, String brand) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(Constants.FORMAT_VALID);
        return PriceResponse.builder().price(prices.getPrice())
                .priority(prices.getPriority())
                .endDate(sdf.format(prices.getEndDate()))
                .startDate(sdf.format(prices.getStartDate()))
                .productName(brand)
                .build();
    }
}
