package com.crudDemo.service;

import com.crudDemo.domain.Pessoa;
import com.crudDemo.service.dto.PessoaDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PessoaService {
   void save(Pessoa p);
    List<Pessoa> findAll();
    Pessoa findById(int id);
    void delete(int id);
    void update(Pessoa p);
    List<Pessoa> findByNome(String nome);
    void atualizar(PessoaDto p,int id);

 void deletarLogico(int id);
}
