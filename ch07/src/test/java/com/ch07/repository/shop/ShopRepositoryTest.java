package com.ch07.repository.shop;

import com.ch07.entity.shop.Customer;
import com.ch07.entity.shop.Order;
import com.ch07.entity.shop.OrderItem;
import com.ch07.entity.shop.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopRepositoryTest {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    // 사용자 등록
    @Test
    void insertUserTest() {
        Customer customer = Customer.builder()
                .custId("c101")
                .name("김유신")
                .hp("010-1234-1001")
                .addr("부산시 동래구")
                .age(23)
                .build();

        customerRepository.save(customer);
    }

    // 상품 등록
    @Test
    void insertProductTest() {
        Product product = Product.builder()
                .prodNo(1)
                .prodName("포카칩")
                .stock(1500)
                .price(2500)
                .company("오리온")
                .build();

        productRepository.save(product);
    }

    // 주문 등록
    @Test
    void insertOrderTest() {

        Customer customer = Customer.builder()
                .custId("c101")
                .build();

        Order order = Order.builder()
                .orderId(1)
                .orderPrice(2500)
                .orderStatus(1)
                .customer(customer)
                .build();

        orderRepository.save(order);
    }

    // 상세주문 등록
    @Test
    void insertOrderItemTest() {

        Order order = Order.builder()
                .orderId(1)
                .build();

        Product product = Product.builder()
                .prodNo(1)
                .build();

        OrderItem orderItem = OrderItem.builder()
                .orderItemId(1)
                .order(order)
                .product(product)
                .count(10)
                .build();

        orderItemRepository.save(orderItem);
    }

}
