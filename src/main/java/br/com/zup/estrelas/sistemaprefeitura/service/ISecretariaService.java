package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

public interface ISecretariaService {

	public MensagemDTO adicionaSecretaria(SecretariaEntity secretaria);

	public SecretariaEntity buscaSecretaria(Long idSecretatia);

	public List<SecretariaEntity> listSecretarias();

	public MensagemDTO removeSecretaria(Long idSecretaria);

	public MensagemDTO alteraSecretaria(Long idSecretaria, AlteraSecretariaDTO secretaria);

}
