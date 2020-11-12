package br.com.zup.estrelas.sistemaprefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;
import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.service.IFuncionarioService;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	IFuncionarioService funcionarioService;
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaFuncionario(@RequestBody FuncionarioEntity idFuncionario) {
		
		return funcionarioService.adicionaFuncionario(idFuncionario);
	}
	
	@GetMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public FuncionarioEntity buscaFuncionario(@PathVariable Long idFuncionario) {

		return funcionarioService.buscaFuncionario(idFuncionario);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<FuncionarioEntity> listaFuncionarios() {

		return funcionarioService.listaFuncionarios();
	}
	
	@DeleteMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO removeFuncionario(@PathVariable Long idFuncionario) {

		return funcionarioService.removeFuncionario(idFuncionario);
	}

	@PutMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO alteraFuncionario(@PathVariable Long idFuncionario, @RequestBody AlteraFuncionarioDTO funcionario) {

		return funcionarioService.alteraFuncionario(idFuncionario, funcionario);
	}

}
