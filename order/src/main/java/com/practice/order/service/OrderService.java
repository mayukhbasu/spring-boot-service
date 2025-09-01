package com.practice.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.order.dto.BookDTO;
import com.practice.order.dto.OrderDTO;
import com.practice.order.entity.Order;
import com.practice.order.events.BookSoldEvent;
import com.practice.order.kafka.BookEventPublisher;
import com.practice.order.mapper.OrderMapper;
import com.practice.order.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookEventPublisher bookEventPublisher;
    private final RestTemplate restTemplate;

    @Transactional
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        BookDTO book = restTemplate.getForObject("http://localhost:8082/books/" + orderDTO.getBookId(), BookDTO.class);
        if (book == null || book.getTitle() == null) {
            throw new RuntimeException("Book ID is invalid or does not exist.");
        }
        log.info("Book is ",book);
        Order order = OrderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        BookSoldEvent event = BookSoldEvent.builder()
                .bookId(savedOrder.getBookId())
                .region(savedOrder.getRegion())
                .quantity(savedOrder.getQuantity())
                .format(savedOrder.getFormat())
                .soldAt(savedOrder.getSoldAt())
                .publisherId(savedOrder.getPublisherId())
                .build();
        bookEventPublisher.publishBookSold(event);
        return OrderMapper.toDto(savedOrder);
    }

    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDto(order);
    }

    public List<OrderDTO> listOrders(Optional<Long> bookId, Optional<String> region) {
        List<Order> orders;

        if (bookId.isPresent() && region.isPresent()) {
            orders = orderRepository.findByBookIdAndRegion(bookId.get(), region.get());
        } else if (bookId.isPresent()) {
            orders = orderRepository.findByBookId(bookId.get());
        } else if (region.isPresent()) {
            orders = orderRepository.findByRegion(region.get());
        } else {
            orders = orderRepository.findAll();
        }

        return orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
