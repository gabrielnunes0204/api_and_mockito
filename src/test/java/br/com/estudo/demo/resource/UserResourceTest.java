package br.com.estudo.demo.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.estudo.demo.domain.User;
import br.com.estudo.demo.domain.dto.UserDTO;
import br.com.estudo.demo.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {

	private static final String EMAIL_CADASTRADO = "E-mail já cadastrado no sistema.";
	private static final int INDEX = 0;
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final Integer ID = 1;
	private static final String NAME = "Gabriel";
	private static final String EMAIL = "gabriel@gmail.com";
	private static final String PASSWORD = "123";
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
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
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(1, NAME, EMAIL, PASSWORD);
	}
}
