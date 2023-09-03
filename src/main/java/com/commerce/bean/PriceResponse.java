package com.commerce.bean;

import lombok.Builder;
import lombok.Data;

/**
 * The price response
 */
@Data
@Builder
public class PriceResponse {

    private String productName;

    private String startDate;

    private String endDate;

    private int priority;

    private double price;
}
