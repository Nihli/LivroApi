package com.project.apiLivros.repository;

import com.project.apiLivros.entity.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {

}
