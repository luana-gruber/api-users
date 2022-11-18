package br.com.cursoudemy.api.resources.exceptions;

import br.com.cursoudemy.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

public class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity(){
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());

    }

    @Test
    void dataIntegratyViolationException(){

    }
}
