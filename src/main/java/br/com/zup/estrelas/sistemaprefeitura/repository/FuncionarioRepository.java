package br.com.zup.estrelas.sistemaprefeitura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.sistemaprefeitura.entity.FuncionarioEntity;

@Repository
public interface FuncionarioRepository extends CrudRepository<FuncionarioEntity, Long> {

}