package br.com.zup.estrelas.sistemaprefeitura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.FuncionarioRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@Service
public class FuncionarioService implements IFuncionarioService {
	
	private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "Funcionário alterado com sucesso.";
	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "Funcionário removido com sucesso!";
	private static final String FUNCIONARIO_JA_CADASTRADO = "O cadastro não ocorreu, funcionário já cadastrado";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	public MensagemDTO adicionaFuncionario(FuncionarioEntity funcionario) {
		
		if(funcionarioRepository.existsById(funcionario.getIdFuncionario())) {
			return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
		}
		funcionarioRepository.save(funcionario);
		return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
	
	}


}
