package org.transportes.vemTransportes.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.transportes.vemTransportes.model.Cartao;
import org.transportes.vemTransportes.service.CartaoService;

@RestController
@RequestMapping(value = "/cartoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartaoController {

	@Autowired
	CartaoService service;

	@GetMapping
	public ResponseEntity<List<Cartao>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cartao> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cartao>> findByNome(@PathVariable String nome) {
		return ResponseEntity.ok().body(service.findByNome(nome));
	}

	@PostMapping
	public ResponseEntity<Cartao> insert(@Valid @RequestBody Cartao cartao) {
		cartao = service.insert(cartao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartao.getId()).toUri();
		return ResponseEntity.created(uri).body(cartao);
	}

	@PutMapping
	public ResponseEntity<Cartao> update(@Valid @RequestBody Cartao cartao) {
		return ResponseEntity.ok().body(service.update(cartao));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
