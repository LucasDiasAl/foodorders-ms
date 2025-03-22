package com.dias.orders.controller;

import com.dias.orders.DTO.OrderDTO;
import com.dias.orders.DTO.StatusDTO;
import com.dias.orders.model.Order;
import com.dias.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/orders")
public class OrderController {

private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public Page<OrderDTO> getAll(@PageableDefault(size = 10) Pageable page) {
        Page<OrderDTO> re = orderService.getAll(page);
        System.out.println(re);
        return re;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO>getById(@PathVariable @NotNull Long id) {
        OrderDTO Odto = orderService.getItemById(id);
        return new ResponseEntity<>(Odto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO Odto, UriComponentsBuilder ucBuilder) {
        OrderDTO newId = orderService.save(Odto);
        URI url = ucBuilder.path("orders/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(url).body(newId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable @NotNull Long id, @RequestBody @Valid StatusDTO status) {
        OrderDTO newOdto = orderService.update(id, status);
        return new ResponseEntity<>(newOdto, HttpStatus.OK);
    }

    @PutMapping("/{id}/payed")
    public ResponseEntity<Void> aprovedPayment(@PathVariable @NotNull Long id) {
        orderService.aprovedOrderPayment(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable @NotNull Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
