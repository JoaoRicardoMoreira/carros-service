package com.crudDemo.rest.web;


import com.crudDemo.domain.Pessoa;
import com.crudDemo.domain.Veiculo;
import com.crudDemo.service.PessoaService;
import com.crudDemo.service.VeiculoService;

import com.crudDemo.service.dto.VeiculoDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class VeiculoResource {
    private final VeiculoService veiculoService;
private final PessoaService pessoaService;

public VeiculoResource(VeiculoService veiculoService, PessoaService pessoaService) {
        this.veiculoService = veiculoService;
        this.pessoaService = pessoaService;
    }


    @PostMapping("/addVeiculo")
    public ResponseEntity<Object> adicionaVeiculo(@Valid @RequestBody VeiculoDto veiculoDto){
        Veiculo veiculos = veiculoService.findByPlaca(veiculoDto.getPlaca());

        if (veiculos != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Veiculo ja cadastrado");
        }
       Veiculo veiculo = veiculoService.montaVeiculo(veiculoDto);
        veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Veiculo cadastrado com sucesso");
    }
    @GetMapping("/veiculo/{id}")
    public ResponseEntity<Object> getVeiculo(@PathVariable int id) {
        Veiculo veiculo = veiculoService.findById(id);
        if(veiculo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(veiculo);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo nao Encontrado");
        }
    }
    @GetMapping("/veiculos/{placa}")
    public ResponseEntity<Object> getVeiculoPlaca(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.findByPlaca(placa);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }
    @PutMapping("/veiculo/editar/{id}")
    public ResponseEntity<Object> editarVeiculo(@Valid @PathVariable int id, @RequestBody VeiculoDto veiculoDto) throws Exception {

        if (veiculoService.findById(id) != null) {
            veiculoService.atualizar(veiculoDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Veiculo atualizado com sucesso");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo nao Encontrado");
        }
    }
    @DeleteMapping("/veiculo/{id}")
    public ResponseEntity<Object> deleteVeiculo(@PathVariable int id) {
        Veiculo veiculo = veiculoService.findById(id); if (veiculo != null && veiculo.getAtivo() != 0) {
            veiculoService.deletarLogico(id);
            return ResponseEntity.status(HttpStatus.OK).body("Veiculo Desativado");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo nao Encontrado ou desativado");
        }
    }

}
