package org.transportes.vemTransportes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.transportes.vemTransportes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsuario(String usuario);

	public List<User> findAllByNomeContainingIgnoreCase(String nome);

}
