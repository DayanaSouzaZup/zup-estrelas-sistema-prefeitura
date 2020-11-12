package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;

import br.com.zup.estrelas.sistemaprefeitura.entity.ProjetoEntity;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

public interface IProjetoService {

	public MensagemDTO adicionaProjeto(ProjetoEntity projeto);

	public ProjetoEntity buscaProjeto(Long idProjeto);

	public List<ProjetoEntity> listaProjetos();

	public MensagemDTO alteraProjeto(Long idProjeto, AlteraProjetoDTO projeto);

}
