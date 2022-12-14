package br.com.cursoudemy.api.services.impl;

import br.com.cursoudemy.api.domain.Users;
import br.com.cursoudemy.api.domain.dto.UserDTO;
import br.com.cursoudemy.api.repositories.UserRepository;
import br.com.cursoudemy.api.services.exceptions.DataIntegrityViolationException;
import br.com.cursoudemy.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID   = 1;
    public static final String LUANA = "Luana";
    public static final String EMAIL = "luu@hotmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    public static final String E_MAIL_JÁ_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;
    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        Users response = service.findById(ID);
        assertNotNull(response);

        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LUANA, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try {
            service.findById(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnUsersList() {
        when(repository.findAll()).thenReturn(List.of(users));
        List<Users> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(LUANA, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateUserThenReturnSuccess() {
        when(repository.save(any())).thenReturn(users);

        Users response = service.create(userDTO);
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LUANA, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateUserThenReturnAnDataIntegrityViolationException(){
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            service.create(userDTO);
        }catch(Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(E_MAIL_JÁ_CADASTRADO_NO_SISTEMA, e.getMessage());
        }
    }

    @Test
    void whenUpdateUserThenReturnSuccess() {
        when(repository.save(any())).thenReturn(users);

        Users response = service.update(userDTO);
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LUANA, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void whenUpdateUserThenReturnAnDataIntegrityViolationException(){
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            service.create(userDTO);
        }catch(Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(E_MAIL_JÁ_CADASTRADO_NO_SISTEMA, e.getMessage());
        }
    }
    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(ID);
    }
    @Test
    void deleteWithObjectNotFoundException(){
        when(repository.findById(ID)).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, e.getMessage());
        }
    }

    private void startUsers (){
        users = new Users(ID, LUANA, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, LUANA, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, LUANA, EMAIL, PASSWORD));
    }
}