package com.crudDemo.repository;

import com.crudDemo.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query(value = "SELECT p.* FROM PESSOAS p WHERE p.nome = :nome", nativeQuery = true)
    List<Pessoa> findByNome(String nome);
}
