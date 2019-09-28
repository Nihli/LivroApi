package com.project.apiLivros.repository;

import com.project.apiLivros.entity.Ano;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnoRepository extends CrudRepository<Ano, Long> {
    Ano findByAno(String ano);

    @Query(value = "SELECT ano FROM ano WHERE codigo=:id",
            nativeQuery = true)
    String findAnoById(@Param("id") Long id);
}
