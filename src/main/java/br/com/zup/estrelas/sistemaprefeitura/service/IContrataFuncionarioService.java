package br.com.zup.estrelas.sistemaprefeitura.service;

import br.com.zup.estrelas.sistemprefeitura.dto.ContrataFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

public interface IContrataFuncionarioService {
	
	MensagemDTO realizaContratacao(ContrataFuncionarioDTO contrataFuncionario);

}
