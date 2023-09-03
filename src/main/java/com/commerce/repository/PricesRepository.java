package com.commerce.repository;

import com.commerce.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * The prices repository
 */
@Repository
public interface PricesRepository extends JpaRepository<Prices, Integer> {

    /**
     * Get prices looking for product id, brand name and period valid
     *
     * @param productId the product id
     * @param brand the brand name
     * @param periodValid the period prices is valid
     * @return list of prices
     */
    @Query(value = "SELECT product_id, brand_id, priority, start_date, end_date, price, price_list, curr FROM prices p left join brand b " +
            "on p.brand_id=b.id WHERE UPPER(name) = UPPER(:brand) and product_id = :productId " +
            "and start_date<(:periodValid) and end_date>(:periodValid)", nativeQuery = true)
    List<Prices> findPricesByProductIdBrandDate(@Param("productId") int productId, @Param("brand") String brand, @Param("periodValid") Timestamp periodValid);

}
