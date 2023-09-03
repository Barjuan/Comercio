package com.commerce.web;

import com.commerce.bean.PriceResponse;
import com.commerce.service.CommerceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

/**
 * The commerce controller
 */
@RestController
@RequestMapping("/v1/commerce")
public class CommerceController {

    @Autowired
    private CommerceService commerceService;

    /**
     * Get price looking for product id, brand name and period valid
     *
     * @param productId the product id
     * @param brand the brand name
     * @param periodValid the period price is valid
     * @return
     */
    @Operation(
            summary = "This method get price",
            description = "This method get price"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    description = "OK", responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = PriceResponse.class,
                                    description = "OK",
                                    example = "OK")))
    })
    @GetMapping(value = "/prices")
    public PriceResponse getPrices(@Parameter(in = ParameterIn.QUERY, description = "Product id", required = true, example = "35455")
                                  @RequestParam(value = "product_id") int productId,
                                   @Parameter(in = ParameterIn.QUERY, description = "Brand", required = true, example = "ZARA")
                                  @RequestParam(value = "brand") String brand,
                                   @Parameter(in = ParameterIn.QUERY, description = "Period Valid", required = true, example = "2020-06-14 15:01:01")
                                  @RequestParam(value = "periodValid") String periodValid) {
        try {
            return commerceService.getPrices(productId, brand, periodValid);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
