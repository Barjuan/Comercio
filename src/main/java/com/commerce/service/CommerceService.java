package com.commerce.service;

import com.commerce.bean.PriceResponse;

/**
 * Te commerce service interface
 */
public interface CommerceService {

    /**
     * Get price looking for product id, brand name and period valid
     *
     * @param productId the product id
     * @param brand the brand name
     * @param periodValid the period price is valid
     * @return the price response
     */
    PriceResponse getPrices(int productId, String brand, String periodValid);
}
