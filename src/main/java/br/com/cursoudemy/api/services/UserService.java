package br.com.cursoudemy.api.services;

import br.com.cursoudemy.api.domain.Users;
import br.com.cursoudemy.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO obj);
    Users update(UserDTO obj);
    void delete(Integer id);
}
