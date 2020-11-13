package br.com.zup.estrelas.sistema.prefeitura.tests;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.zup.estrelas.sistemaprefeitura.entity.ProjetoEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.ProjetoRepository;
import br.com.zup.estrelas.sistemaprefeitura.service.ProjetoService;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoServiceTests {
	
	private static final String PROJETO_ALTERADO_COM_SUCESSO = "Projeto alterado com sucesso";
	private static final String PROJETO_INEXISTENTE = "Projeto inexistente";

	@Mock
	ProjetoRepository projetoRepository;
	
	@InjectMocks
	ProjetoService projetoService;
	
	@Test
	public void deveAlterarUmProjeto() {
		
		ProjetoEntity projeto = new ProjetoEntity();
		
		Optional<ProjetoEntity> projetoArmazenado = Optional.of(projeto);
		
		AlteraProjetoDTO alteraProjeto = new AlteraProjetoDTO();
		
		MensagemDTO mensagemRetornada = projetoService.alteraProjeto(2L, alteraProjeto);
		MensagemDTO mensagemEsperada = new MensagemDTO(PROJETO_ALTERADO_COM_SUCESSO);
		
		Assert.assertEquals("Projeto alterado com sucesso", mensagemEsperada, mensagemRetornada);
		
	}
	
	@Test
	public void naoDeveAlterarUmProjeto() {

		ProjetoEntity peca = new ProjetoEntity();

		Optional<ProjetoEntity> projetoArmazenado = Optional.empty();

		Mockito.when(projetoRepository.existsById(2L)).thenReturn(false);

		AlteraProjetoDTO alteraProjeto = new AlteraProjetoDTO();

		MensagemDTO mensagemRetornada = projetoService.alteraProjeto(2L, alteraProjeto);
		MensagemDTO mensagemEsperada = new MensagemDTO(PROJETO_INEXISTENTE);

		Assert.assertEquals("Não deve alterar um projeto inexistente", mensagemEsperada, mensagemRetornada);
	}
}
