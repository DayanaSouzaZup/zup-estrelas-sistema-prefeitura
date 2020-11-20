package br.com.zup.estrelas.sistemaprefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.sistemaprefeitura.entity.ProjetoEntity;
import br.com.zup.estrelas.sistemaprefeitura.service.IProjetoService;
import br.com.zup.estrelas.sistemprefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.sistemprefeitura.dto.MensagemDTO;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

	@Autowired
	IProjetoService projetoService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO adicionaProjeto(@RequestBody ProjetoEntity idProjeto) {

		return projetoService.adicionaProjeto(idProjeto);
	}

	@GetMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProjetoEntity buscaProjeto(@PathVariable Long idProjeto) {

		return projetoService.buscaProjeto(idProjeto);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ProjetoEntity> listaProjetos() {

		return projetoService.listaProjetos();
	}

	@PutMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO alteraProjeto(@PathVariable Long idProjeto, @RequestBody AlteraProjetoDTO projeto) {

		return projetoService.alteraProjeto(idProjeto, projeto);
	}
	
	//FIXME: Faltou o endpoint de finalizar.

	
	
}
