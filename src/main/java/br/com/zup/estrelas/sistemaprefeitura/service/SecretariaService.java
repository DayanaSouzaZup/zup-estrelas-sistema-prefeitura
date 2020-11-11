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

	private static final String SECRETARIA_ALTERADA_COM_SUCESSO = "Peça alterada com sucesso.";
	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Peça removida com sucesso!";
	private static final String SECRETARIA_JA_CADASTRADA = "O cadastro não ocorreu, peça já está cadastrada";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String SECRETARIA_INEXISTENTE = "Peça inexistente.";

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaSecretaria(SecretariaEntity secretaria) {

		if (secretariaRepository.existsById(secretaria.getIdSecretaria())) {
			return new MensagemDTO(SECRETARIA_JA_CADASTRADA);

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
