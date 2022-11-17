package br.com.cursoudemy.api.resources;

import br.com.cursoudemy.api.domain.Users;
import br.com.cursoudemy.api.domain.dto.UserDTO;
import br.com.cursoudemy.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID   = 1;
    public static final String LUANA = "Luana";
    public static final String EMAIL = "luu@hotmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    @InjectMocks
    private UserResource resource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl service;

    private UserDTO userDTO;

    private Users users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers (){
        users = new Users(ID, LUANA, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, LUANA, EMAIL, PASSWORD);
    }
}