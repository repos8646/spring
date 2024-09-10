package com.ch07.entity.shop;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int orderPrice;
    private int orderStatus;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderCustomer")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
