package br.com.estudo.demo.resource.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.estudo.demo.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	
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
	void dataIntegratyViolationException() {
		
	}
}
