package com.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());

	}

@GetMapping("/{id}")
public ResponseEntity<Categoria> getById(@PathVariable Long id) {
	return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

@GetMapping("/tema/{tema}")
public ResponseEntity<List<Categoria>> getByTema(@PathVariable String tema) {
	return ResponseEntity.ok(categoriaRepository.findAllByTemaContainingIgnoreCase(tema));
}

@PostMapping
public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(cartegoria));
}

@PutMapping
public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {

	if (categoriaRepository.existsById(categoria.getId()))
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	else
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {

	if (categoriaRepository.existsById(id))
		categoriaRepository.deleteById(id);
	else
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
}
