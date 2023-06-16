package com.codecool.backend.products.shoppingcart;

import com.codecool.backend.products.shoppingcart.CartItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;




    public ShoppingCart(Long userId) {
        this.userId = userId;
        this.cartItems = new ArrayList<>();
        this.total = getTotal();
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shoppingCart", orphanRemoval = true)
    private List<CartItem> cartItems;


    private double total;



    public void addItem(CartItem cartItem) {
        var checkForOrderItem = findOrderItem(cartItem);
        if (checkForOrderItem != null) {
            checkForOrderItem.increaseQuantity();
        } else {
            cartItems.add(cartItem);
        }
    }

    public CartItem findOrderItem(CartItem cartItem) {
        return cartItems.get(cartItems.indexOf(cartItem));
    }

    public void decreaseItem(CartItem cartItem) {
        var checkForOrderItem = findOrderItem(cartItem);
        if (checkForOrderItem.getQuantity() > 0) {
            checkForOrderItem.decreaseQuantity();
        } else {
            cartItems.remove(cartItem);
        }
    }

    public void removeOrderItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public double getTotal() {
        return cartItems.stream().mapToDouble(cartItem -> cartItem.getProduct().getPrice()).sum();
    }
}
