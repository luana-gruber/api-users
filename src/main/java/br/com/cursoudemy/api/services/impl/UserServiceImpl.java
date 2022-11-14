package br.com.cursoudemy.api.services.impl;

import br.com.cursoudemy.api.domain.Users;
import br.com.cursoudemy.api.domain.dto.UserDTO;
import br.com.cursoudemy.api.repositories.UserRepository;
import br.com.cursoudemy.api.services.UserService;
import br.com.cursoudemy.api.services.exceptions.DataIntegratyViolationException;
import br.com.cursoudemy.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    public List<Users> findAll(){
        return repository.findAll();
    }
    @Override
    public Users create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    private void findByEmail(UserDTO obj){
        Optional<Users> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }
}
