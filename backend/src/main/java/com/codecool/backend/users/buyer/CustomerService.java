package com.codecool.backend.users.buyer;

import com.codecool.backend.fileStorage.ImageService;
import com.codecool.backend.products.orders.OrderRequest;
import com.codecool.backend.products.orders.OrderDAO;
import com.codecool.backend.products.orders.OrderDTO;
import com.codecool.backend.products.orders.OrderForm;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.repository.AppUserDTOMapper;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customer")
public class CustomerService extends AppUserService {

    private final OrderDAO orderDAO;

    @Autowired
    public CustomerService(@Qualifier("jpa") AppUserDao appUserDao,
                           AppUserDTOMapper userDTOMapper,
                           PasswordEncoder passwordEncoder,
                           ImageService imageService,
                           OrderDAO orderDAO, JWTService jwtService) {
        super(appUserDao, userDTOMapper, passwordEncoder, imageService, jwtService);
        this.orderDAO = orderDAO;
    }

    public OrderDTO makeOrder(OrderForm orderRequest, Long userId) {
OrderRequest newOrder=new OrderRequest().builder()
        .cartItems(orderRequest.items())
        .description(orderRequest.description())
        .address(orderRequest.address())
        .intent(orderRequest.intent())
        .userId(userId)
        .currency(orderRequest.currency())
        .build();
        return orderDAO.addOrder(newOrder);
    }

    public List<OrderDTO> getOrdersForThisCustomer(Long userId){
        return orderDAO.getAllOrdersByUser(userId);
    }

}
