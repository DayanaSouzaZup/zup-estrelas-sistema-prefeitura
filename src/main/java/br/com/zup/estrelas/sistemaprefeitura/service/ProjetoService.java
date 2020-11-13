package br.com.zup.estrelas.sistemaprefeitura.service;

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
	private static final String PROJETO_JA_CADASTRADO = "O cadastro não ocorreu, projeto já cadastrado";
	private static final String PROJETO_CADASTRADO_COM_SUCESSO = "Projeto cadastrado com sucesso.";
	private static final String PROJETO_INEXISTENTE = "Projeto inexistente.";
	private static final String NAO_HÁ_ORCAMENTO_PARA_ESSE_PROJETO = "Não há orçamento para esse projeto";
	private static final String SECRETARIA_INEXISTENTE = "Secretaria inexistente";
	private static final String VALOR_DE_CUSTO_MENOR_QUE_ZERO = "Valor de custo menor que zero";
	private static final String PROJETO_SEM_SECRETARIA = "Projeto sem secretaria";
	private static final String DATA_ENTREGA_DEVE_SER_NULA = "Data de entrega do projeto deverá ser nula";
	private static final String PROJETO_AINDA_NAO_CONCLUIDO = "Projeto ainda não concluído";

	@Autowired
	ProjetoRepository projetoRepository;

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaProjeto(ProjetoEntity projeto) {

		if (projetoRepository.existsById(projeto.getIdProjeto())) {
			return new MensagemDTO(PROJETO_JA_CADASTRADO);
		}

		if (projeto.getSecretaria() == null && projeto.getSecretaria().getIdSecretaria() == null) {
			return new MensagemDTO(PROJETO_SEM_SECRETARIA);
		}
		
		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(projeto.getSecretaria().getIdSecretaria());
		SecretariaEntity secretaria = secretariaOptional.get();

		if (secretaria == null) {
			return new MensagemDTO(SECRETARIA_INEXISTENTE);
		}
		
		if(projeto.getCusto() < 0) {
			return new MensagemDTO(VALOR_DE_CUSTO_MENOR_QUE_ZERO);
		}
		if (projeto.getCusto() > secretaria.getOrcamentoProjetos()) {

			return new MensagemDTO(NAO_HÁ_ORCAMENTO_PARA_ESSE_PROJETO);
		}
		
		if(projeto.getDataEntrega() != null) {
			return new MensagemDTO(DATA_ENTREGA_DEVE_SER_NULA);
		}
		
		if(projeto.getConcluido() == true) {
			return new MensagemDTO(PROJETO_AINDA_NAO_CONCLUIDO);
		}

		secretaria.setOrcamentoProjetos(secretaria.getOrcamentoProjetos() - projeto.getCusto());
		
		secretariaRepository.save(secretaria);
		
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

}
