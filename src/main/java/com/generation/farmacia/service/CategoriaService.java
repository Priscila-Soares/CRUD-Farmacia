package com.generation.farmacia.service;

import com.generation.model.Categoria;
import com.generation.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarCategorias() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Categoria criarCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

    public Optional<Categoria> atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        return repository.findById(id).map(categoria -> {
            categoria.setNome(categoriaAtualizada.getNome());
            categoria.setDescricao(categoriaAtualizada.getDescricao());
            return repository.save(categoria);
        });
    }

    public boolean deletarCategoria(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}