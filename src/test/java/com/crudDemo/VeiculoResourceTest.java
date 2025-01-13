package com.crudDemo;

import com.crudDemo.domain.Veiculo;
import com.crudDemo.rest.web.VeiculoResource;
import com.crudDemo.service.PessoaService;
import com.crudDemo.service.VeiculoService;
import com.crudDemo.service.dto.VeiculoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VeiculoResourceTest {

    @InjectMocks
    private VeiculoResource veiculoResource;

    @Mock
    private VeiculoService veiculoService;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionaVeiculo_Sucesso() {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setPlaca("ABC1234");

        when(veiculoService.findByPlaca(veiculoDto.getPlaca())).thenReturn(null);
        when(veiculoService.montaVeiculo(veiculoDto)).thenReturn(new Veiculo());

        ResponseEntity<Object> response = veiculoResource.adicionaVeiculo(veiculoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Veiculo cadastrado com sucesso", response.getBody());
        verify(veiculoService, times(1)).save(any(Veiculo.class));
    }

    @Test
    public void testAdicionaVeiculo_Falha_VeiculoExistente() {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setPlaca("ABC1234");

        when(veiculoService.findByPlaca(veiculoDto.getPlaca())).thenReturn(new Veiculo());

        ResponseEntity<Object> response = veiculoResource.adicionaVeiculo(veiculoDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Veiculo ja cadastrado", response.getBody());
    }

    @Test
    public void testGetVeiculo_Sucesso() {
        Veiculo veiculo = new Veiculo();
        when(veiculoService.findById(1)).thenReturn(veiculo);

        ResponseEntity<Object> response = veiculoResource.getVeiculo(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    public void testGetVeiculo_Falha_IdInexistente() {
        when(veiculoService.findById(1)).thenReturn(null);

        ResponseEntity<Object> response = veiculoResource.getVeiculo(90);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Veiculo nao Encontrado", response.getBody());
    }

    @Test
    public void testEditarVeiculo_Sucesso() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        when(veiculoService.findById(1)).thenReturn(new Veiculo());

        ResponseEntity<Object> response = veiculoResource.editarVeiculo(1, veiculoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Veiculo atualizado com sucesso", response.getBody());
        verify(veiculoService, times(1)).atualizar(veiculoDto, 1);
    }

    @Test
    public void testEditarVeiculo_Falha_IdInexistente() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        when(veiculoService.findById(1)).thenReturn(null);

        ResponseEntity<Object> response = veiculoResource.editarVeiculo(1, veiculoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Veiculo nao Encontrado", response.getBody());
    }

    @Test
    public void testDeleteVeiculo_Sucesso() {
        Veiculo veiculo = new Veiculo();
        veiculo.setAtivo(1);
        when(veiculoService.findById(1)).thenReturn(veiculo);

        ResponseEntity<Object> response = veiculoResource.deleteVeiculo(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Veiculo Desativado", response.getBody());
        verify(veiculoService, times(1)).deletarLogico(1);
    }

    @Test
    public void testDeleteVeiculo_Falha_IdInexistente() {
        when(veiculoService.findById(1)).thenReturn(null);

        ResponseEntity<Object> response = veiculoResource.deleteVeiculo(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Veiculo nao Encontrado ou desativado", response.getBody());
    }

    @Test
    public void testDeleteVeiculo_Falha_VeiculoDesativado() {
        Veiculo veiculo = new Veiculo();
        veiculo.setAtivo(0);
        when(veiculoService.findById(1)).thenReturn(veiculo);

        ResponseEntity<Object> response = veiculoResource.deleteVeiculo(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Veiculo nao Encontrado ou desativado", response.getBody());
    }
}
