package com.commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * The entity price class
 */
@Entity
@Table(name = "prices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_list")
    private int priceList;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "priority")
    private int priority;

    @Column(name = "price")
    private double price;

    @Column(name = "curr")
    private String curr;
}
