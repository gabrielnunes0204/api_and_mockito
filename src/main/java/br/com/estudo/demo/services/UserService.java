package br.com.estudo.demo.services;

import br.com.estudo.demo.domain.User;
import br.com.estudo.demo.domain.dto.UserDTO;

public interface UserService {

	User findById(Integer id);
	java.util.List<User> findAll();
	User create(UserDTO obj);
}
