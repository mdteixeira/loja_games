package com.generation.mdteixeira.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
import org.springframework.web.server.ResponseStatusException;

import com.generation.mdteixeira.model.Produto;
import com.generation.mdteixeira.repository.CategoriaRepository;
import com.generation.mdteixeira.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	// GetAll --> retorna todos os itens
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// GetById --> Busca item por seu Id
	@GetMapping("/{Id}")
	public ResponseEntity<Produto> getById(@PathVariable Long Id) {
		return produtoRepository.findById(Id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// GetByNome --> pega o item pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// post --> envia um post para o server a partir do body
		@PostMapping
		public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe.", null);
		}

		// Put --> atualiza um post que já existe
		@PutMapping
		public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
			if (produtoRepository.existsById(produto.getId())) {
				if (categoriaRepository.existsById(produto.getCategoria().getId()))
					return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe.", null);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// delete --> deleta um post a partir do id
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT) // devolve o status 204 - no content
		public void delete(@PathVariable Long id) {
			Optional<Produto> produto = produtoRepository.findById(id);

			if (produto.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			produtoRepository.deleteById(id);
		}
		
		

}
