package com.tsoft.ecomm.order.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "inventory-service", url = "${inventory-service.url}")

public interface FeignClient {

        Logger log = LoggerFactory.getLogger(FeignClient.class);

        @GetMapping("/v1/api/inventory/{skuCode}")
        @CircuitBreaker(name = "inventory", fallbackMethod = "getInventoryFallback")
        @Retry(name = "inventory")
        public Integer getInventory(@PathVariable String skuCode);

        default Integer getInventoryFallback(String skuCode, Throwable t) {
                log.info("Fallback method called");
                return -1;
        }
}
