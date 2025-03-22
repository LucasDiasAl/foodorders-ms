package com.dias.orders.repository;

import com.dias.orders.model.Order;
import com.dias.orders.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order p set p.status = :status where p = :order")
    void updateStatus(Status status, Order order);

    @Query("SELECT o from Order o LEFT JOIN FETCH o.items where o.id = :id")
    Order getByIdWithItems(Long id);
}
