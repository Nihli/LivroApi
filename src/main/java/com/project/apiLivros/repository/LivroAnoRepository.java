package com.project.apiLivros.repository;

import com.project.apiLivros.entity.LivroAno;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LivroAnoRepository extends CrudRepository<LivroAno, Long> {
    Optional<LivroAno> findByIdLivro(Long idLivro);
}
