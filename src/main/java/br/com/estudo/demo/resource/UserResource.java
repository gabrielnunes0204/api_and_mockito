package br.com.estudo.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.estudo.demo.domain.dto.UserDTO;
import br.com.estudo.demo.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private  ModelMapper mapper;
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listDTO =  service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
