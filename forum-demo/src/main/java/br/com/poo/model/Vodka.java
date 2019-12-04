package br.com.poo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="vod")
public class Vodka extends Bebida {

	
	private int qntDestilado;

	public int getQntDestilado() {
		return qntDestilado;
	}

	public void setQntDestilado(int qntDestilado) {
		this.qntDestilado = qntDestilado;
	}
	
	
}
