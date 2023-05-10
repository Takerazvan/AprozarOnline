package com.codecool.aprozar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ShoppingCart {

    private Customer customer;
    private List<OrderItem> orderItems;
    private double total;

    public void addItem(OrderItem orderItem){
        var checkForOrderItem=findOrderItem(orderItem);
        if (checkForOrderItem!=null){
            checkForOrderItem.increaseQuantity();
        } else {
            orderItems.add(orderItem);
        }
    }

    public OrderItem findOrderItem(OrderItem orderItem){
        return orderItems.get(orderItems.indexOf(orderItem));
    }
    public void decreaseItem(OrderItem orderItem){
        var checkForOrderItem=findOrderItem(orderItem);
        if (checkForOrderItem.getQuantity()>0){
            checkForOrderItem.decreaseQuantity();
        }
        else {
            orderItems.remove(orderItem);
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }


}
