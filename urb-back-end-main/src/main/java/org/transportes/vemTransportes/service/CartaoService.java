package org.transportes.vemTransportes.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transportes.vemTransportes.model.Cartao;
import org.transportes.vemTransportes.model.Usuario;
import org.transportes.vemTransportes.repository.CartaoRepository;
import org.transportes.vemTransportes.repository.UsuarioRepository;
import org.transportes.vemTransportes.service.exceptions.DatabaseException;
import org.transportes.vemTransportes.service.exceptions.ResourceNotFoundException;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public List<Cartao> findAll() {
		return cartaoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Cartao findById(Long id) {
		Optional<Cartao> obj = cartaoRepository.findById(id);

		Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado!"));

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Cartao> findByNome(String nm) {
		List<Cartao> list = cartaoRepository.findAllByNmContainingIgnoreCase(nm);

		return list;
	}

	@Transactional
	public Cartao insert(Cartao entity) {
		Optional<Usuario> obj = usuarioRepository.findById(entity.getUsuario().getId());
		obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
		entity = cartaoRepository.save(entity);
		return entity;
	}

	@Transactional
	public Cartao update(Cartao entity) {
		Optional<Cartao> obj = cartaoRepository.findById(entity.getId());
		Cartao cartao = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		entity = cartaoRepository.save(entity);

		return cartao;
	}

	public void delete(Long id) {
		try {
			cartaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}

}
