package com.project.apiLivros.repository;

import com.project.apiLivros.entity.Ano;
import org.springframework.data.repository.CrudRepository;

public interface AnoRepository extends CrudRepository<Ano, Long> {
    Ano findByAno(String ano);
}
