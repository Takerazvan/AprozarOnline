package com.codecool.aprozar.model.Produce;

import com.codecool.aprozar.model.Produce.OrderItem;
import com.codecool.aprozar.model.Users.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {




    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        this.total = getTotal();
    }

    private Customer customer;

    private List<OrderItem> orderItems;
    private double total;



    public void addItem(OrderItem orderItem) {
        var checkForOrderItem = findOrderItem(orderItem);
        if (checkForOrderItem != null) {
            checkForOrderItem.increaseQuantity();
        } else {
            orderItems.add(orderItem);
        }
    }

    public OrderItem findOrderItem(OrderItem orderItem) {
        return orderItems.get(orderItems.indexOf(orderItem));
    }

    public void decreaseItem(OrderItem orderItem) {
        var checkForOrderItem = findOrderItem(orderItem);
        if (checkForOrderItem.getQuantity() > 0) {
            checkForOrderItem.decreaseQuantity();
        } else {
            orderItems.remove(orderItem);
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public double getTotal() {
        return orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice()).sum();
    }
}
