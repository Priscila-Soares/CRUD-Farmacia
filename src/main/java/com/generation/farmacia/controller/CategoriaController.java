package com.generation.farmacia.controller;

import com.generation.farmacia.model.Categoria;

import com.generation.farmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


	@RestController
	@RequestMapping("/categorias")
	public class CategoriaController {

	    @Autowired
	    private CategoriaRepository repository;

	    @GetMapping
	    public List<Categoria> listarCategorias() {
	        return repository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
	        return repository.findById(id).map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Categoria criar(@RequestBody Categoria categoria) {
	        return repository.save(categoria);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
	        return repository.findById(id).map(c -> {
	            c.setNome(categoria.getNome());
	            c.setDescricao(categoria.getDescricao());
	            repository.save(c);
	            return ResponseEntity.ok(c);
	        }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletar(@PathVariable Long id) {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.notFound().build();
	    }
	}

