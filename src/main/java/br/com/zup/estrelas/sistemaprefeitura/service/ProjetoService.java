package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.entity.ProjetoEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.ProjetoRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

public class ProjetoService implements IProjetoService {

	private static final String PROJETO_ALTERADO_COM_SUCESSO = "Projeto alterado com sucesso.";
	private static final String PROJETO_JA_CADASTRADO = "O cadastro não ocorreu, projeto já cadastrado";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String PROJETO_INEXISTENTE = "Projeto inexistente.";

	@Autowired
	ProjetoRepository projetoRepository;

	public MensagemDTO adicionaProjeto(ProjetoEntity projeto) {

		if (projetoRepository.existsById(projeto.getIdProjeto())) {
			return new MensagemDTO(PROJETO_JA_CADASTRADO);
		}
		projetoRepository.save(projeto);
		return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

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
