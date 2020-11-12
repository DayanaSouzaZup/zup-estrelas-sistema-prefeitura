package br.com.zup.estrelas.sistemaprefeitura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sistemaprefeitura.repository.ContrataFuncionarioRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.ContrataFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@Service
public class ContrataFuncionarioService implements IContrataFuncionarioService{
	
	@Autowired
	ContrataFuncionarioRepository contrataFuncionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Override
	public MensagemDTO realizaContratacao(ContrataFuncionarioDTO contrataFuncionario) {
		return null;
	}

}
