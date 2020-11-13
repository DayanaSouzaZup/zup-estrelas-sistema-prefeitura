package br.com.zup.estrelas.sistema.prefeitura.tests;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.FuncionarioRepository;
import br.com.zup.estrelas.sistemaprefeitura.service.FuncionarioService;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceTests {

	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "Funcionário removido com sucesso";
	private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso";

	@Mock
	FuncionarioRepository funcionarioRepository;

	@InjectMocks
	FuncionarioService funcionarioService;

	@Test
	public void deveRemoverFuncionario() {

		FuncionarioEntity funcionario = new FuncionarioEntity();
		
		funcionario.setIdFuncionario(2L);

		Mockito.when(funcionarioRepository.existsById(funcionario.getIdFuncionario())).thenReturn(false);

		MensagemDTO mensagemRetornada = funcionarioService.removeFuncionario(funcionario.getIdFuncionario());
		MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
		
		Assert.assertEquals("Não deve remover um funcionário inexistente", mensagemEsperada, mensagemRetornada);

	}
	
	@Test
	public void naoDeveRemoverFuncionario() {

		FuncionarioEntity funcionario = new FuncionarioEntity();

		Mockito.when(funcionarioRepository.existsById(funcionario.getIdFuncionario())).thenReturn(true);

		MensagemDTO mensagemRetornada = funcionarioService.removeFuncionario(2L);
		MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_INEXISTENTE);

		Assert.assertEquals("Não deve remover um funcionário inexistente", mensagemEsperada, mensagemRetornada);

	}
	
	@Test
	public void deveAdicionarUmFuncionario() {

		FuncionarioEntity funcionario = new FuncionarioEntity();

		funcionario.setIdFuncionario(2L);
		funcionario.setNome("João");
		funcionario.setCpf("87646673");
		funcionario.setSalario(10000.00);
		funcionario.setIdSecretaria(2L);
		funcionario.setFuncao("Enfermeiro");
		funcionario.setConcursado(true);
		funcionario.setDataAdmissao(LocalDate.now());

		
		Mockito.when(funcionarioRepository.existsById(funcionario.getIdFuncionario())).thenReturn(false);

		MensagemDTO mensagemRetornada = funcionarioService.adicionaFuncionario(funcionario);
		MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

		Assert.assertEquals(CADASTRO_REALIZADO_COM_SUCESSO, mensagemEsperada, mensagemRetornada);

	}

}
