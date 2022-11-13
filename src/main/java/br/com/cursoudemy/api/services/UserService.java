package br.com.cursoudemy.api.services;

import br.com.cursoudemy.api.domain.User;
import br.com.cursoudemy.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
