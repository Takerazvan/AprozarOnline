package com.codecool.backend.products.orders;

import com.codecool.backend.products.Product;
import com.codecool.backend.products.ProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class OrderService implements OrderDAO {
    private OrderRepository orderRepository;
    private OrderDTOMapper orderDTOMapper;
    private ProductDAO productDAO;



    @Override
    public List<OrderDTO> getAllOrdersByUser(Long id) {
        return orderRepository.findAllByUserId(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("user with id [%s] hasn't placed any orders ".formatted(id)))
                .stream().map(orderDTOMapper)
                .collect(Collectors.toList());
    }


    private void TakeProductsOutOfStock(List<CartItem> items) {
        for (CartItem item:
             items) {
         Product product= productDAO.findProductById(item.getProduct().getId())
                    .orElseThrow(()->
                            new ResourceNotFoundException("product with id [%s] not found "
                                    .formatted(item.getProduct().getId())));
         product.setQuantity(product.getQuantity()- item.getQuantity());
         if (product.getQuantity()<=0){
             productDAO.deleteProductById(product.getId());
         }

        }
    }

    @Override
    public OrderDTO addOrder(Order order) {
        return null;
    }

    @Override
    public OrderDTO findOrderById(Long orderID) {
        return orderRepository.findById(orderID).map(orderDTOMapper)
                .orElseThrow(()->new ResourceNotFoundException ("order with id [%s] not found".formatted(orderID)));
    }
}
