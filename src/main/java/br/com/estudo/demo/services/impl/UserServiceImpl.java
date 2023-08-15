package br.com.estudo.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.estudo.demo.domain.User;
import br.com.estudo.demo.domain.dto.UserDTO;
import br.com.estudo.demo.repositories.UserRepository;
import br.com.estudo.demo.services.UserService;
import br.com.estudo.demo.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}
	
	@Override
	public User create(UserDTO obj) {
		return repository.save(mapper.map(obj, User.class));
	}
}
