package com.dias.orders.service;

import com.dias.orders.DTO.OrderDTO;
import com.dias.orders.DTO.StatusDTO;
import com.dias.orders.model.Order;
import com.dias.orders.model.Status;
import com.dias.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Page<OrderDTO> getAll(Pageable page) {
        return repository.findAll(page).map(o -> modelMapper.map(o, OrderDTO.class));
    }

    public OrderDTO getItemById(Long id) {
        Order order = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO save(OrderDTO dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        Order finalOrder = order;
        order.getItems().forEach(i -> i.setOrder(finalOrder));

        Order Saveorder = repository.save(order);

        return modelMapper.map(Saveorder, OrderDTO.class);
    }

    public OrderDTO update(Long id, StatusDTO Sdto) {
        Order order = repository.getByIdWithItems(id);
        if (order == null) {
            throw new EntityNotFoundException();
        }
        order.setStatus(Sdto.getStatus());
        repository.updateStatus(Sdto.getStatus(), order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public void aprovedOrderPayment(Long id) {
        Order order = repository.getByIdWithItems(id);
        if (order == null) {
            throw new EntityNotFoundException();
        }
        repository.updateStatus(Status.APPROVED, order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
