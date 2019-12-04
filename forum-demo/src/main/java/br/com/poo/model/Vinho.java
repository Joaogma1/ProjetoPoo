package br.com.poo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
@DiscriminatorValue(value = "Vin")
@Entity
public class Vinho extends Bebida {

	private String idade;


	@Enumerated(EnumType.STRING)
	private TipoVinho tipo;


	public String getIdade() {
		return idade;
	}


	public void setIdade(String idade) {
		this.idade = idade;
	}


	public TipoVinho getTipo() {
		return tipo;
	}


	public void setTipo(TipoVinho tipo) {
		this.tipo = tipo;
	}
	
	
}
