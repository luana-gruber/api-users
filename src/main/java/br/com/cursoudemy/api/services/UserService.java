package br.com.cursoudemy.api.services;

import br.com.cursoudemy.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
