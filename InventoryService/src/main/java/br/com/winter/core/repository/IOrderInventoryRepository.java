package br.com.winter.core.repository;

import br.com.winter.core.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {

    List<OrderInventory> findByOrderIdAndTransactionId(String orderId, String transactionId);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);
}
