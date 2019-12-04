package br.com.poo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@DiscriminatorValue(value = "Lic")
@Entity
public class Licor extends Bebida {

}
