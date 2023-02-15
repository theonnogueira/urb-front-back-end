package org.transportes.vemTransportes.service;

import java.nio.charset.Charset;

import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.transportes.vemTransportes.dto.UserLogin;
import org.transportes.vemTransportes.model.User;
import org.transportes.vemTransportes.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> cadastrarUser(User user) {
		if (userRepository.findByUsuario(user.getNome()).isPresent())
			return Optional.empty();
		user.setSenha(criptografarSenha(user.getSenha()));
		return Optional.of(userRepository.save(user));
	}

	public Optional<User> atualizarUser(User user) {

		if (userRepository.findById(user.getId()).isPresent()) {
			Optional<User> buscaUser = userRepository.findByUsuario(user.getUsuario());

			if ((buscaUser.isPresent()) && (buscaUser.get().getId() != user.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			user.setSenha(criptografarSenha(user.getSenha()));
			return Optional.ofNullable(userRepository.save(user));
		}
		return Optional.empty();
	}

	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> userLogin) {
		Optional<User> user = userRepository.findByUsuario(userLogin.get().getUsuario());
		if (user.isPresent()) {

			if (compararSenha(userLogin.get().getSenha(), user.get().getSenha())) {
				userLogin.get().setId(user.get().getId());
				userLogin.get().setNome(user.get().getNome());
				userLogin.get().setToken(gerarBasicToken(userLogin.get().getUsuario(), userLogin.get().getSenha()));
				userLogin.get().setSenha(user.get().getSenha());

				return userLogin;
			}
		}
		return Optional.empty();
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private boolean compararSenha(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String user, String senha) {
		String token = user + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic" + new String(tokenBase64);
	}
}