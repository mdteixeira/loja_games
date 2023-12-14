package com.generation.mdteixeira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.mdteixeira.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
	List<Produto> findAllByPrecoGreaterThan(@Param("preco") float preco);
}
