package com.tsoft.ecomm.indexer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "order")
public class Order {
    @Id
    private Long id;
    @Field(name = "order_number")
    private String orderNumber;
    @Field(name = "customer_name")
    private String customerName;
    @Field(name = "customer_email")
    private String customerEmail;
    @Field(name = "order_amount")
    private BigDecimal orderAmount;
    private BigDecimal discountAmount;

}
