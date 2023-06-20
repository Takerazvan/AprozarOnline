//package com.codecool.backend.products.shoppingcart;
//
//import com.codecool.backend.products.orders.CartItem;
//import lombok.AllArgsConstructor;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ShoppingCartService implements ShoppingCartDAO {
//    private final ShoppingCartRepository shoppingCartRepository;
//    private final ShoppingCartDTOMapper shoppingCartDTOMapper;
//
//    @Override
//    public ShoppingCartDTO findCartByUserId(Long userId) {
//        return shoppingCartRepository.findByUserId(userId).map(shoppingCartDTOMapper)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("customer with id [%s] doesn't have a car".formatted(userId)));
//    }
//
//    private ShoppingCart getCart(Long userId) {
//        return shoppingCartRepository.findByUserId(userId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("customer with id [%s] doesn't have a car".formatted(userId)));
//    }
//
//    @Override
//    public void addCartItem(CartItem item, Long userId) {
//
//
//        getCart(userId).getCartItems().add(item);
//    }
//
//    @Override
//    public void removeCartItem(Long productId, Long userId) {
//        ShoppingCart cart = getCart(userId);
//        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
//    }
//
//    @Override
//    public CartItem findCartItemBtProductId(Long productId, Long userId) {
//        return getCart(userId)
//                .getCartItems()
//                .stream()
//                .filter(item -> item.getProduct().getId().equals(productId))
//                .findFirst().orElseThrow(() -> new ResourceNotFoundException("product not added yet"));
//    }
//
//    @Override
//    public void updateItemQuantityByProductId(Long productId, Integer quantity, Long userId) {
//        if (quantity > 0) {
//            findCartItemBtProductId(productId, userId).setQuantity(quantity);
//        } else removeCartItem(productId, userId);
//    }
//
//    @Override
//    public void emptyCartItems(Long userId) {
//getCart(userId).getCartItems().clear();
//    }
//
//}
