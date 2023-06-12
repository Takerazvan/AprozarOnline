package com.codecool.backend.users;

import com.codecool.backend.products.ShoppingCartRepository;
import com.codecool.backend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    private final AppUserDTOMapper userDTOMapper = new AppUserDTOMapper();
    @Mock
    private AppUserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    private CustomerService uut;

    @BeforeEach
    void setUp() {
        uut = new CustomerService(userDao, userDTOMapper, passwordEncoder, shoppingCartRepository);
    }

    @Test
    void getAllCustomers() {
        uut.getAllCustomers();
        verify(userDao).getAllCustomers();
    }


}
