package br.com.zup.estrelas.sistemaprefeitura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.sistemaprefeitura.entity.SecretariaEntity;

@Repository
public interface SecretariaRepository extends CrudRepository<SecretariaEntity, Long> {

}
