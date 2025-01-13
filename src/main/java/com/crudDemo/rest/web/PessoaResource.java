package com.crudDemo.rest.web;

import com.crudDemo.domain.Pessoa;
import com.crudDemo.repository.PessoaRepository;
import com.crudDemo.service.PessoaService;
import com.crudDemo.service.dto.PessoaDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/addPessoa")
    public ResponseEntity<Object> adicionaPessoa(@Valid @RequestBody  Pessoa pessoa) throws Exception {
        List<Pessoa> pessoas = pessoaService.findByNome(pessoa.getNome());
   if (!pessoas.isEmpty()) {
     return ResponseEntity.status(HttpStatus.CONFLICT).body("Pessoa ja cadastrada");
   }
        pessoaService.save(pessoa);
   return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa cadastrada com sucesso");
    }
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Object> getPessoa(@PathVariable int id) {
        Pessoa pessoa = pessoaService.findById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }
    @GetMapping("/pessoas/{nome}")
    public ResponseEntity<Object> getPessoaByNome(@PathVariable String nome) {
        List<Pessoa> pessoas = pessoaService.findByNome(nome);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }
    @PutMapping("/pessoa/editar/{id}")
    public ResponseEntity<Object> editarPessoa(@Valid @PathVariable int id, @RequestBody PessoaDto pessoaDto) throws Exception {

        if (pessoaService.findById(id) != null) {
            pessoaService.atualizar(pessoaDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa atualizada com sucesso");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao Encontrada");
        }
    }
    @DeleteMapping("/pessoa/{id}")
public ResponseEntity<Object> deletePessoa(@PathVariable int id) {
        Pessoa pessoa = pessoaService.findById(id); if (pessoa != null && pessoa.getAtivo() != 0) {
        pessoaService.deletarLogico(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa Desativada");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao Encontrada ou desativada");
        }
}

}
