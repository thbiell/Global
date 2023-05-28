package br.com.fiap.global.model.entities.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	Page<User> findAllByAtivoTrue(Pageable paginacao);
}
