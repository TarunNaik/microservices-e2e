package com.tsoft.ecomm.order.dto;

import java.math.BigDecimal;

public record OrderRequest(String skuCode, BigDecimal price, Integer quantity, UserDetail userDetail) {
    public record UserDetail(String firstName, String lastName, String email){

    }
}
