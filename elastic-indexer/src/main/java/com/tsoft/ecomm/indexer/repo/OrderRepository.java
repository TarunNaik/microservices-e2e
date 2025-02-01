package com.tsoft.ecomm.indexer.repo;

import com.tsoft.ecomm.indexer.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface OrderRepository extends ElasticsearchRepository<Order, Long> {
}
