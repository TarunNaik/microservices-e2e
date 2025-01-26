package com.tsoft.ecomm.order.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "inventory-service", url = "${inventory-service.url}")
public interface FeignClient {

        @GetMapping("/v1/api/inventory/{skuCode}")
        public Integer getInventory(@PathVariable String skuCode);
}
