package br.com.estudo.demo.resource.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import br.com.estudo.demo.services.exceptions.DataIntegrityViolationException;
import br.com.estudo.demo.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final String EMAIL_CADASTRADO = "E-mail já cadastrado";	
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
	}

	@Test
	void whenObjectNotFoundThenReturnAResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objetNotFound(
						new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
						new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}
	
	@Test
	void dataIntegrityViolationException() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegratyViolationException(
						new DataIntegrityViolationException(EMAIL_CADASTRADO),
						new MockHttpServletRequest());
		
		assertNotEquals("user/2", response.getBody().getPath());
		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(EMAIL_CADASTRADO, response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}
}
