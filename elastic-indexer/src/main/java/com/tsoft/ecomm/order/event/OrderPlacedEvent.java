package com.tsoft.ecomm.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String email;
    private String firstName;
    private String lastName;
    private BigDecimal orderAmount;
    private BigDecimal discountAmount;
}
