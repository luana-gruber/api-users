package br.com.cursoudemy.api.services;

import br.com.cursoudemy.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
