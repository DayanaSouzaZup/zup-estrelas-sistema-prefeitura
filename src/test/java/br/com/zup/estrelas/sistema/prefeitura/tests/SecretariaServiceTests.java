package br.com.zup.estrelas.sistema.prefeitura.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.SecretariaRepository;
import br.com.zup.estrelas.sistemaprefeitura.service.SecretariaService;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RunWith(MockitoJUnitRunner.class)
public class SecretariaServiceTests {

	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String SECRETARIA_JA_CADASTRADA = "Já existe uma secretaria com esse ID";

	@Mock
	SecretariaRepository secretariaRepository;

	@InjectMocks
	SecretariaService secretariaService;

	@Test
	public void deveAdicionarUmaSecretaria() {

		SecretariaEntity secretaria = new SecretariaEntity();

		secretaria.setIdSecretaria(2L);
		secretaria.setArea("SAUDE");
		secretaria.setOrcamentoProjetos(10000.00);
		secretaria.setOrcamentoFolha(100000.00);
		secretaria.setTelefone("3075-9876");
		secretaria.setEndereco("Rua das Flores, 876");
		secretaria.setSite("www.saudesp.com.br");
		secretaria.setEmail("saudesp@saudesp.com.br");

		
		Mockito.when(secretariaRepository.existsById(secretaria.getIdSecretaria())).thenReturn(false);

		MensagemDTO mensagemRetornada = secretariaService.adicionaSecretaria(secretaria);
		MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

		Assert.assertEquals(CADASTRO_REALIZADO_COM_SUCESSO, mensagemEsperada, mensagemRetornada);

	}
	
	@Test
	public void naoDeveAdicionarUmaSecretaria() {

		SecretariaEntity secretaria = new SecretariaEntity();

		secretaria.setIdSecretaria(2L);
		secretaria.setArea("SAUDE");
		secretaria.setOrcamentoProjetos(10000.00);
		secretaria.setOrcamentoFolha(100000.00);
		secretaria.setTelefone("3075-9876");
		secretaria.setEndereco("Rua das Flores, 876");
		secretaria.setSite("www.saudesp.com.br");
		secretaria.setEmail("saudesp@saudesp.com.br");

		
		Mockito.when(secretariaRepository.existsById(secretaria.getIdSecretaria())).thenReturn(true);

		MensagemDTO mensagemRetornada = secretariaService.adicionaSecretaria(secretaria);
		MensagemDTO mensagemEsperada = new MensagemDTO(SECRETARIA_JA_CADASTRADA);

		Assert.assertEquals(SECRETARIA_JA_CADASTRADA, mensagemEsperada, mensagemRetornada);

	}

}
