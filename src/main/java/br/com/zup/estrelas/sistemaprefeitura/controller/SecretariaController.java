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

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;
import br.com.zup.estrelas.sistemaprefeitura.service.ISecretariaService;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {

	@Autowired
	ISecretariaService secretariaService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO adicionaSecretaria(@RequestBody SecretariaEntity idSecretaria) {

		return secretariaService.adicionaSecretaria(idSecretaria);
	}

	@GetMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SecretariaEntity buscaSecretaria(@PathVariable Long idSecretaria) {

		return secretariaService.buscaSecretaria(idSecretaria);

	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SecretariaEntity> listaSecretarias() {

		return secretariaService.listSecretarias();
	}

	@DeleteMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO removeSecretaria(@PathVariable Long idSecretaria) {

		return secretariaService.removeSecretaria(idSecretaria);
	}

	@PutMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO alteraSecretaria(@PathVariable Long idSecretaria, @RequestBody AlteraSecretariaDTO secretaria) {

		return secretariaService.alteraSecretaria(idSecretaria, secretaria);
	}

}
