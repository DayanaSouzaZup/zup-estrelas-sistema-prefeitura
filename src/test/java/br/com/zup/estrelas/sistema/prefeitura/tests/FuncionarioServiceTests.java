package br.com.zup.estrelas.sistema.prefeitura.tests;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.FuncionarioRepository;
import br.com.zup.estrelas.sistemaprefeitura.service.FuncionarioService;

public class FuncionarioServiceTests {


	@Mock
	FuncionarioRepository funcionarioRepository;
	
	@InjectMocks
	FuncionarioService funcionarioService;
	
	@Test
	public void deveRemoverFuncionario() {
		FuncionarioEntity funcionario = new FuncionarioEntity();
		
		Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(true);
		
	}
}
