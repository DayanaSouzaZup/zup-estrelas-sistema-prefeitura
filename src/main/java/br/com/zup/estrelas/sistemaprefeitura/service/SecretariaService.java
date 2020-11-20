package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.SecretariaRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@Service
public class SecretariaService implements ISecretariaService {

	private static final String SECRETARIA_ALTERADA_COM_SUCESSO = "Secretaria alterada com sucesso.";
	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Secretaria removida com sucesso!";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String SECRETARIA_INEXISTENTE = "Secretaria inexistente.";
	private static final String ID_JÁ_UTILIZADO = "Já existe uma secretaria com esse ID";

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaSecretaria(SecretariaEntity secretaria) {

	    // FIXME: Day, aqui nós deveríamos criar um DTO como primeiro ponto de barreira
	    // para que o ID não seja nem possível de ser passado na requisição.
	    // Depois disso, a validação proposta na regra de negócio era a de que fosse validada a
	    // área, para que não hajam duas secretarias para a mesma área.
		if (secretaria.getIdSecretaria() != null) {

			if (secretariaRepository.existsById(secretaria.getIdSecretaria())) {
				return new MensagemDTO(ID_JÁ_UTILIZADO);
			}

		}

		secretariaRepository.save(secretaria);

		return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

	}

	public SecretariaEntity buscaSecretaria(Long idSecretaria) {

		return secretariaRepository.findById(idSecretaria).orElse(null);
	}

	public List<SecretariaEntity> listSecretarias() {
		return (List<SecretariaEntity>) secretariaRepository.findAll();
	}

	public MensagemDTO removeSecretaria(Long idSecretaria) {

		if (secretariaRepository.existsById(idSecretaria)) {
			secretariaRepository.deleteById(idSecretaria);
			return new MensagemDTO(SECRETARIA_REMOVIDA_COM_SUCESSO);
		}
		return new MensagemDTO(SECRETARIA_INEXISTENTE);
	}

	public MensagemDTO alteraSecretaria(Long idSecretaria, AlteraSecretariaDTO alteraSecretariaDTO) {

		Optional<SecretariaEntity> secretariaConsultada = secretariaRepository.findById(idSecretaria);

		if (secretariaConsultada.isPresent()) {

			SecretariaEntity secretariaAlterada = secretariaConsultada.get();

			secretariaAlterada.setArea(alteraSecretariaDTO.getArea());
			secretariaAlterada.setOrcamentoProjetos(alteraSecretariaDTO.getOrcamentoProjetos());
			secretariaAlterada.setOrcamentoFolha(alteraSecretariaDTO.getOrcamentoFolha());
			secretariaAlterada.setTelefone(alteraSecretariaDTO.getTelefone());
			secretariaAlterada.setEndereco(alteraSecretariaDTO.getEndereco());
			secretariaAlterada.setSite(alteraSecretariaDTO.getSite());
			secretariaAlterada.setEmail(alteraSecretariaDTO.getEmail());

			secretariaRepository.save(secretariaAlterada);
			return new MensagemDTO(SECRETARIA_ALTERADA_COM_SUCESSO);

		}

		return new MensagemDTO(SECRETARIA_INEXISTENTE);
	}

}
