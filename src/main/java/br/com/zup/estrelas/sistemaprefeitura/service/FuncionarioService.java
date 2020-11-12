package br.com.zup.estrelas.sistemaprefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.repository.FuncionarioRepository;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@Service
public class FuncionarioService implements IFuncionarioService {

	private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "Funcionário alterado com sucesso.";
	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "Funcionário removido com sucesso!";
	private static final String FUNCIONARIO_JA_CADASTRADO = "O cadastro não ocorreu, funcionário já cadastrado";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
	private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";

	@Autowired
	FuncionarioRepository funcionarioRepository;

	public MensagemDTO adicionaFuncionario(FuncionarioEntity funcionario) {

		if (funcionarioRepository.existsById(funcionario.getIdFuncionario())) {
			return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
		}
		funcionarioRepository.save(funcionario);
		return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

	}

	public FuncionarioEntity buscaFuncionario(Long idFuncionario) {

		return funcionarioRepository.findById(idFuncionario).orElse(null);

	}

	public List<FuncionarioEntity> listaFuncionarios() {

		return (List<FuncionarioEntity>) funcionarioRepository.findAll();
	}

	public MensagemDTO removeFuncionario(Long idFuncionario) {

		if (funcionarioRepository.existsById(idFuncionario)) {
			funcionarioRepository.deleteById(idFuncionario);
			return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
		}

		return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
	}

	public MensagemDTO alteraFuncionario(Long idFuncionario, AlteraFuncionarioDTO alteraFuncionarioDTO) {

		Optional<FuncionarioEntity> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

		if (funcionarioConsultado.isPresent()) {

			FuncionarioEntity funcionarioAlterado = funcionarioConsultado.get();

			funcionarioAlterado.setNome(alteraFuncionarioDTO.getNome());
			funcionarioAlterado.setCpf(alteraFuncionarioDTO.getCpf());
			funcionarioAlterado.setSalario(alteraFuncionarioDTO.getSalario());
			funcionarioAlterado.setFuncao(alteraFuncionarioDTO.getFuncao());
			funcionarioAlterado.setConcursado(alteraFuncionarioDTO.getConcursado());
			funcionarioAlterado.setFuncao(alteraFuncionarioDTO.getFuncao());
			funcionarioAlterado.setDataAdmissao(alteraFuncionarioDTO.getDataAdmissao());

			funcionarioRepository.save(funcionarioAlterado);
			return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);

		}
		return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
	}

}
