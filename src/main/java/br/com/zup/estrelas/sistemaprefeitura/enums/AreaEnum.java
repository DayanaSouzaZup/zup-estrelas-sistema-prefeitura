package br.com.zup.estrelas.sistemaprefeitura.enums;

public enum AreaEnum {

	EDUCACAO("educacao"), TRANSITO("transito"), SAUDE("saude");

	private String value;

	AreaEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
