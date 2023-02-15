package org.transportes.vemTransportes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.transportes.vemTransportes.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tb_cartoes WHERE tb_cartoes.nm LIKE %:nm%")
	List<Cartao> findAllByNmContainingIgnoreCase(String nm);

}
