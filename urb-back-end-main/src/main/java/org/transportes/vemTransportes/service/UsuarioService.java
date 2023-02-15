package org.transportes.vemTransportes.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transportes.vemTransportes.model.Usuario;
import org.transportes.vemTransportes.repository.UsuarioRepository;
import org.transportes.vemTransportes.service.exceptions.DatabaseException;
import org.transportes.vemTransportes.service.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario getById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);

		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getByName(String apelido) {
		List<Usuario> list = usuarioRepository.findAllByApelidoContainingIgnoreCase(apelido);

		return list;
	}

	@Transactional
	public Usuario insert(Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional
	public Usuario update(Usuario entity) {
		Optional<Usuario> obj = usuarioRepository.findById(entity.getId());

		Usuario tema = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		tema = usuarioRepository.save(tema);

		return tema;
	}

	public void delete(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
}
