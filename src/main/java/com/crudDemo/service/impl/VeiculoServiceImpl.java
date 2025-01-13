package com.crudDemo.service.impl;

import com.crudDemo.domain.Pessoa;
import com.crudDemo.domain.Veiculo;
import com.crudDemo.repository.VeiculoRepository;
import com.crudDemo.service.PessoaService;
import com.crudDemo.service.VeiculoService;
import com.crudDemo.service.dto.VeiculoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final PessoaService pessoaService;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, PessoaService pessoaService) {
        this.veiculoRepository = veiculoRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public void save(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    @Override
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo findById(int id) {
        if(veiculoRepository.existsById(id)) {
            return veiculoRepository.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    @Override
    public void atualizar(VeiculoDto veiculoDto, int id) {
        Veiculo veiculo = veiculoRepository.findById(id).get();
        Pessoa pessoa = pessoaService.findById(veiculoDto.getFk_pessoa());
        if (pessoa != null) {
            veiculo.setPessoa(pessoa);
        }
        //criar uma exepction para jogar mensagens de error
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setMarca(veiculoDto.getMarca());
        veiculo.setModelo(veiculoDto.getModelo());
        veiculo.setAtivo(1);
        veiculoRepository.save(veiculo);
    }

    @Override
    public void deletarLogico(int id) {
        Veiculo veiculo = veiculoRepository.findById(id).get();
        veiculo.setAtivo(0);
        veiculoRepository.save(veiculo);
    }

    @Override
    public Veiculo montaVeiculo(VeiculoDto veiculoDto) {
        Veiculo veiculo = new Veiculo();
        Pessoa pessoa =pessoaService.findById(veiculoDto.getFk_pessoa());
        if(pessoa != null){
            veiculo.setPessoa(pessoa);
        }
        veiculo.setModelo(veiculoDto.getModelo());
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setMarca(veiculoDto.getMarca());
        veiculo.setAtivo(1);
        return veiculo;
    }
}
