package com.crudDemo.service;

import com.crudDemo.domain.Pessoa;
import com.crudDemo.domain.Veiculo;
import com.crudDemo.service.dto.PessoaDto;
import com.crudDemo.service.dto.VeiculoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VeiculoService {
    void save(Veiculo veiculo);
    List<Veiculo> findAll();
    Veiculo findById(int id);
    Veiculo findByPlaca(String placa);
    void atualizar(VeiculoDto veiculoDto, int id);
    void deletarLogico(int id);
    Veiculo montaVeiculo(VeiculoDto veiculoDto);
}
