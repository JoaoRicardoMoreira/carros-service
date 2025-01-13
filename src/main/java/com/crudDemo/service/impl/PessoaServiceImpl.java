package com.crudDemo.service.impl;

import com.crudDemo.domain.Pessoa;
import com.crudDemo.repository.PessoaRepository;
import com.crudDemo.service.PessoaService;
import com.crudDemo.service.dto.PessoaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {


    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    @Override
    public void save(Pessoa p) {
        pessoaRepository.save(p);

    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa findById(int id) {

       if( pessoaRepository.existsById(id) ){
           return pessoaRepository.findById(id).get();
       }else return null;
    }

    @Override
    public void delete(int id) {
      pessoaRepository.deleteById(id);
    }

    @Override
    public void update(Pessoa p) {
        pessoaRepository.save(p);
    }

    @Override
    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNome(nome);
    }

    @Override
    public void atualizar(PessoaDto p,int id) {
            Pessoa pessoa = pessoaRepository.findById(id).get();
            pessoa.setNome(p.getNome());
            pessoa.setCpf(p.getCpf());
            pessoa.setRg(p.getRg());
            pessoa.setTelefone(p.getTelefone());
            pessoaRepository.save(pessoa);



        }

    @Override
    public void deletarLogico(int id) {
        Pessoa pessoa = pessoaRepository.findById(id).get();
        pessoa.setAtivo(0);
        pessoaRepository.save(pessoa);
    }

}

