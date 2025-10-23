package br.com.winter.core.repository;

import br.com.winter.core.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IEventRepository extends MongoRepository<Event, String> {

    List<Event> findAllByOrderByCreatedAtDesc();

    Optional<Event> findTopByOrderIdOrderByCreatedAtDesc(String orderId);

    Optional<Event> findTopByTransactionIdOrderByCreatedAtDesc(String transactionId);
}
