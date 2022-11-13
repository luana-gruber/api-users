package br.com.cursoudemy.api.repositories;

import br.com.cursoudemy.api.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
