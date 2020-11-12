package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

public interface IFuncionarioService {
	
	public MensagemDTO adicionaFuncionario(FuncionarioEntity funcionario);

	public FuncionarioEntity buscaFuncionario(Long idFuncionario);

	public List<FuncionarioEntity> listaFuncionarios();

	public MensagemDTO removeFuncionario(Long idFuncionario);

	public MensagemDTO alteraFuncionario(Long idFuncionario, AlteraFuncionarioDTO funcionario);

	
}
