package br.com.zup.estrelas.sistema.prefeitura.tests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.SecretariaRepository;
import br.com.zup.estrelas.sistemaprefeitura.service.SecretariaService;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;


public class SecretariaServiceTests {
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";

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
		
		Mockito.when(secretariaRepository.existsById(2L)).thenReturn(false);
		
		MensagemDTO mensagemRetornada = secretariaService.adicionaSecretaria(secretaria);
		MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
		
		//Assert.assertEquals("Secretaria deve ser cadastrada com sucesso", mensagemEsperada, mensagemRetornada);
		
		
		
	}

}
