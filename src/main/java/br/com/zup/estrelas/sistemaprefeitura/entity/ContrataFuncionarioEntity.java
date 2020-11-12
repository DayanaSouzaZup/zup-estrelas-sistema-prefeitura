package br.com.zup.estrelas.sistemaprefeitura.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "contratacao")
public class ContrataFuncionarioEntity {

	@Id
	@Column(name = "id_contratacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContratacao;

	@ManyToOne
	@JoinColumn(name = "area", foreignKey = @ForeignKey(name = "FK_AREA"))
	private SecretariaEntity secretaria;

	private String nome;

	private String cpf;

	private Double salario;

	private String funcao;

	private Boolean concursado;

	private LocalDate dataAdmissao;

	public Long getIdContratacao() {
		return idContratacao;
	}

	public void setIdContratacao(Long idContratacao) {
		this.idContratacao = idContratacao;
	}

	public SecretariaEntity getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(SecretariaEntity secretaria) {
		this.secretaria = secretaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Boolean getConcursado() {
		return concursado;
	}

	public void setConcursado(Boolean concursado) {
		this.concursado = concursado;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

}
