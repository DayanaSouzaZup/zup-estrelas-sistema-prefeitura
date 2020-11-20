package br.com.zup.estrelas.sistemaprefeitura.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sistemaprefeitura.entity.ProjetoEntity;
import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.ProjetoRepository;
import br.com.zup.estrelas.sistemaprefeitura.repository.SecretariaRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@Service
public class ProjetoService implements IProjetoService {

	private static final String PROJETO_ALTERADO_COM_SUCESSO = "Projeto alterado com sucesso.";
	private static final String PROJETO_CADASTRADO_COM_SUCESSO = "Projeto cadastrado com sucesso.";
	private static final String PROJETO_INEXISTENTE = "Projeto inexistente.";
	private static final String NAO_HÁ_ORCAMENTO_PARA_ESSE_PROJETO = "Não há orçamento para esse projeto";
	private static final String SECRETARIA_INEXISTENTE = "Secretaria inexistente";
	private static final String VALOR_DE_CUSTO_MENOR_QUE_ZERO = "Valor de custo menor que zero";
	private static final String PROJETO_SEM_SECRETARIA = "Projeto sem secretaria";
	private static final String DATA_ENTREGA_DEVE_SER_NULA = "Data de entrega do projeto deverá ser nula";
	private static final String PROJETO_AINDA_NAO_CONCLUIDO = "Projeto ainda não concluído";
	private static final String DESCRICAO_NAO_PODE_SER_VAZIA = "Descrição não pode ser vazia";
	private static final String DESCRICAO_ALTERADA_COM_SUCESSO = "Descrição alterada com sucesso";
	private static final String PROJETO_CONCLUIDO_COM_SUCESSO = "Projeto concluído com sucesso";
	private static final String DATA_ENTREGA_NAO_PODE_SER_MENOR_DATA_INICIO = "Data de entrega não pode ser menos que a data de início";
	private static final String ID_JÁ_UTILIZADO = "Já existe um projeto com esse ID";

	@Autowired
	ProjetoRepository projetoRepository;

	@Autowired
	SecretariaRepository secretariaRepository;

	// FIXME: Day, este método está bem grande, dele é possível extrair alguns 
	// métodos privados (lembrar das funções) para deixar o código mais limpo.
	public MensagemDTO adicionaProjeto(ProjetoEntity projeto) {

	    // FIXME: Na criação com um DTO essa validação não é necessária.
		if (projeto.getIdProjeto() != null) {

			if (projetoRepository.existsById(projeto.getIdSecretaria())) {
				return new MensagemDTO(ID_JÁ_UTILIZADO);
			}
		}

		if (projeto.getSecretaria() == null && projeto.getIdSecretaria() == null) {
			return new MensagemDTO(PROJETO_SEM_SECRETARIA);
		}

		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(projeto.getIdSecretaria());
		SecretariaEntity secretaria = secretariaOptional.get();

		if (secretaria == null) {
			return new MensagemDTO(SECRETARIA_INEXISTENTE);
		}

		projeto.setSecretaria(secretaria);

		if (projeto.getCusto() < 0) {
			return new MensagemDTO(VALOR_DE_CUSTO_MENOR_QUE_ZERO);
		}
		if (projeto.getCusto() > secretaria.getOrcamentoProjetos()) {
			return new MensagemDTO(NAO_HÁ_ORCAMENTO_PARA_ESSE_PROJETO);
		}

		secretaria.setOrcamentoProjetos(secretaria.getOrcamentoProjetos() - projeto.getCusto());

		if (projeto.getDataEntrega() != null) {
			return new MensagemDTO(DATA_ENTREGA_DEVE_SER_NULA);
		}

		if (projeto.getConcluido()) {
			return new MensagemDTO(PROJETO_AINDA_NAO_CONCLUIDO);
		}

		// FIXME: Aqui é importante setar o concluido como false também.
		projeto.setDataInicio(LocalDate.now());

		projetoRepository.save(projeto);
		return new MensagemDTO(PROJETO_CADASTRADO_COM_SUCESSO);

	}

	public ProjetoEntity buscaProjeto(Long idProjeto) {

		return projetoRepository.findById(idProjeto).orElse(null);

	}

	public List<ProjetoEntity> listaProjetos() {

		return (List<ProjetoEntity>) projetoRepository.findAll();
	}

	public MensagemDTO alteraProjeto(Long idProjeto, AlteraProjetoDTO alteraProjetoDTO) {

		Optional<ProjetoEntity> projetoConsultado = projetoRepository.findById(idProjeto);

		if (projetoConsultado.isPresent()) {

			ProjetoEntity projetoAlterado = projetoConsultado.get();

			projetoAlterado.setDescricao(alteraProjetoDTO.getDescricao());

			projetoRepository.save(projetoAlterado);

			return new MensagemDTO(PROJETO_ALTERADO_COM_SUCESSO);

		}
		return new MensagemDTO(PROJETO_INEXISTENTE);
	}

	// FIXME: Esse método não é usado.
	public MensagemDTO alteraProjetoSecretaria(Long idProjeto, String descricao) {

		if (descricao == null || descricao.isEmpty()) {
			return new MensagemDTO(DESCRICAO_NAO_PODE_SER_VAZIA);
		}

		Optional<ProjetoEntity> projetoOptional = projetoRepository.findById(idProjeto);
		
		// FIXME: Antes de fazer um get temos que verificar se ele existe usando o 
		// método isPresent().
		ProjetoEntity projeto = projetoOptional.get();

		if (projeto == null) {

			return new MensagemDTO(PROJETO_INEXISTENTE);
		}

		projeto.setDescricao(descricao);

		projetoRepository.save(projeto);

		return new MensagemDTO(DESCRICAO_ALTERADA_COM_SUCESSO);

	}

	// FIXME: Faltou criar um endpoint para utilizar este método.
	public MensagemDTO projetoConcluido(Long idProjeto, LocalDate dataEntega) {

		Optional<ProjetoEntity> projetoOptional = projetoRepository.findById(idProjeto);
		
		// FIXME: Antes de fazer um get temos que verificar se ele existe usando o 
		// método isPresent().
		ProjetoEntity projeto = projetoOptional.get();

		if (projeto == null) {
			return new MensagemDTO(PROJETO_INEXISTENTE);
		}

		if (projeto.getDataInicio().compareTo(dataEntega) > 0) {

			return new MensagemDTO(DATA_ENTREGA_NAO_PODE_SER_MENOR_DATA_INICIO);
		}

		//FIXME: Faltou setar a data de entrega.
		projeto.setConcluido(true);

		return new MensagemDTO(PROJETO_CONCLUIDO_COM_SUCESSO);
	}

}
