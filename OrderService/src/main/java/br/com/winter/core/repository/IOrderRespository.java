package br.com.winter.core.repository;

import br.com.winter.core.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOrderRespository extends MongoRepository<Order, String> {
}
